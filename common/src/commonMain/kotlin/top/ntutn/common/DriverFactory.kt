package top.ntutn.common

import com.squareup.sqldelight.db.SqlDriver
import top.ntutn.MyDatabase

expect class DriverFactory {
    expect fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): MyDatabase {
    val driver = driverFactory.createDriver()
    val database = MyDatabase(driver)

    // Do more work with the database (see below).
    return database
}