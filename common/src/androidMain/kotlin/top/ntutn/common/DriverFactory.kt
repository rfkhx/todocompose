package top.ntutn.common

import android.content.Context
import com.google.auto.service.AutoService
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import top.ntutn.MyDatabase

@AutoService(IDriverFactory::class)
class DriverFactory: IDriverFactory {
    override fun createDriver(): SqlDriver {
        val context = TODO()
        return AndroidSqliteDriver(MyDatabase.Schema, context, "todo.db")
    }
}
