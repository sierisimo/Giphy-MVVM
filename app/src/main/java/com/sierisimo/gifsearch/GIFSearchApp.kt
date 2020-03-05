package com.sierisimo.gifsearch

import android.app.Application
import com.sierisimo.gifsearch.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GIFSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()

        configureInjection()
    }

    private fun configureInjection() {
        startKoin {
            androidLogger()
            androidContext(this@GIFSearchApp)
            androidFileProperties()
            modules(AppModules())
        }
    }
}