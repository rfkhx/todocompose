package top.ntutn.common

import AppInfo
import com.google.auto.service.AutoService
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import top.ntutn.MyDatabase
import top.ntutn.common.datasource.IDriverFactory
import java.io.File
import java.util.*

@AutoService(IDriverFactory::class)
class DriverFactory: IDriverFactory {
    private val OS_NAME = System.getProperty("os.name").lowercase(Locale.getDefault())

    private val currentConfigDir by lazy {
        val configFolder = if (OS_NAME == "windows") {
            File(System.getenv("APPDATA"), "hrtodo")
        } else {
            File(System.getProperty("user.home"), ".config/hrtodo")
        }
        configFolder.mkdirs()
        configFolder
    }

    private val sqliteFile get() = File(currentConfigDir, if(AppInfo.CHANNEL == "local_test") "todo_test.db" else "todo.db")
    private val sqliteUrl get() = "jdbc:sqlite:${sqliteFile.canonicalPath}"

    override fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(sqliteUrl)
        initDatabase(driver)
        return driver
    }

    private fun initDatabase(driver: SqlDriver) {
        val currentVer = getVersion(driver)
        if (currentVer == 0) {
            MyDatabase.Schema.create(driver)
            setVersion(driver,1)
            println("init: created tables, setVersion to 1")
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
    }

    private fun getVersion(driver: SqlDriver): Int {
        val sqlCursor = driver.executeQuery(null, "PRAGMA user_version;", 0, null)
        return sqlCursor.use { sqlCursor.getLong(0)!!.toInt() }
    }

    private fun setVersion(driver: SqlDriver, version: Int) {
        driver.execute(null, String.format("PRAGMA user_version = %d;", version), 0, null)
    }
}