package com.lutawav.architecturestudy

import android.app.Application
import com.lutawav.architecturestudy.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }


    companion object {
        lateinit var instance: MainApplication
            private set
    }
}