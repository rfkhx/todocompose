package top.ntutn.common

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import top.ntutn.MyDatabase

// in src/jvmMain/kotlin
actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        MyDatabase.Schema.create(driver)
        return driver
    }
}