package top.ntutn.android

import android.app.Application
import top.ntutn.common.AppUtil

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtil.init(this)
    }
}