package top.ntutn.common

import com.squareup.sqldelight.db.SqlDriver
import top.ntutn.MyDatabase
import java.util.ServiceLoader

interface IDriverFactory {
    companion object {
        val instance get() = ServiceLoader.load(IDriverFactory::class.java).first()
    }

    fun createDriver(): SqlDriver
}

fun createDatabase(): MyDatabase {
    val driver = IDriverFactory.instance.createDriver()
    val database = MyDatabase(driver)

    // Do more work with the database (see below).
    return database
}