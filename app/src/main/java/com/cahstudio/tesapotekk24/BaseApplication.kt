package com.cahstudio.tesapotekk24

import android.app.Application
import com.cahstudio.tesapotekk24.di.apiModule
import com.cahstudio.tesapotekk24.di.networkModule
import com.cahstudio.tesapotekk24.di.repositoryModule
import com.cahstudio.tesapotekk24.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@BaseApplication)
            modules(listOf(viewModelModule, repositoryModule, apiModule, networkModule))
        }
    }
}