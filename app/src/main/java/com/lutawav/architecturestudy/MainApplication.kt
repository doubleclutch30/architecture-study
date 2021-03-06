package com.lutawav.architecturestudy

import android.app.Application
import com.lutawav.architecturestudy.module.apiModule
import com.lutawav.architecturestudy.module.localDataModule
import com.lutawav.architecturestudy.module.repositoryModule
import com.lutawav.architecturestudy.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) {
                    Level.DEBUG
                } else {
                    Level.NONE
                }
            )
            androidContext(this@MainApplication)
            modules(
                apiModule,
                localDataModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}