package com.allen.weather.app

import android.app.Application
import android.os.HandlerThread
import android.os.Looper
import com.allen.weather.di.ApplicationComponent
import com.allen.weather.di.DaggerApplicationComponent

class MyApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger2()
    }

    private fun initDagger2() {
        applicationComponent = DaggerApplicationComponent.builder()
                .context(this)
                .build()
        applicationComponent.inject(this)
    }
}
