package top.ntutn.common

import android.content.Context

object AppUtil {
    lateinit var applicationContext: Context

    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
    }
}

val applicationContext get() = AppUtil.applicationContext