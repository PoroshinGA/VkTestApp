package com.example.vktestapp

import android.app.Application
import com.example.vktestapp.core.network.di.provideNetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class VkTestApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@VkTestApp)
            modules(provideNetworkModule)
        }
    }
}