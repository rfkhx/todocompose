package top.ntutn.common

import com.google.auto.service.AutoService
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import top.ntutn.MyDatabase
import top.ntutn.common.datasource.IDriverFactory

@AutoService(IDriverFactory::class)
class DriverFactory: IDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MyDatabase.Schema, applicationContext, "todo.db")
    }
}
