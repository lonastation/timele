package com.linn.timele

import android.app.Application
import com.linn.timele.data.AppContainer
import com.linn.timele.data.AppDataContainer

class TimeLeApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}