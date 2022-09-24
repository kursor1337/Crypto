package com.kursor.crypto

import android.app.Application
import com.kursor.crypto.di.appModule
import com.kursor.crypto.di.dataModule
import com.kursor.crypto.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule, domainModule, dataModule)
        }

    }

}