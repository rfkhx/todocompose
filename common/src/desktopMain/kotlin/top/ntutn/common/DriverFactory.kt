package top.ntutn.common

import com.google.auto.service.AutoService
import com.squareup.sqldelight.db.SqlCursor
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import top.ntutn.MyDatabase
import java.io.File
import java.util.*

// in src/jvmMain/kotlin
@AutoService(IDriverFactory::class)
class DriverFactory: IDriverFactory {
    private val OS_NAME = System.getProperty("os.name").lowercase(Locale.getDefault())

    val currentConfigDir by lazy {
        val configFolder = if (OS_NAME == "windows") {
            File(System.getenv("APPDATA"), "hrtodo")
        } else {
            File(System.getProperty("user.home"), ".config/hrtodo")
        }
        configFolder.mkdirs()
        configFolder
    }

    val sqliteFile get() = File(currentConfigDir, "todo.db")
    val sqliteUrl get() = "jdbc:sqlite:${sqliteFile.canonicalPath}"

    override fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(sqliteUrl)
        initDatabase(driver)
        return driver
    }

    private fun initDatabase(driver: SqlDriver) {
        val currentVer = getVersion(driver)
        if (currentVer == 0) {
            setVersion(driver,1)
            println("init: created tables, setVersion to 1");
        } else {
            val schemaVer = MyDatabase.Schema.version
            if (schemaVer > currentVer) {
                MyDatabase.Schema.migrate(driver, currentVer, schemaVer)
                setVersion(driver, schemaVer)
                println("init: migrated from $currentVer to $schemaVer")
            } else {
                println("init")
            }
        }
        val database = MyDatabase.invoke(driver)
    }

    private fun getVersion(driver: SqlDriver): Int {
        val sqlCursor = driver.executeQuery(null, "PRAGMA user_version;", 0, null)
        return sqlCursor.use { sqlCursor.getLong(0)!!.toInt() }
    }

    private fun setVersion(driver: SqlDriver, version: Int) {
        driver.execute(null, String.format("PRAGMA user_version = %d;", version), 0, null)
    }
}