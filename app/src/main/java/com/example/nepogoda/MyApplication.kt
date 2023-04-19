package com.example.nepogoda

import android.app.Application
import com.example.nepogoda.infrastructure.InfrastructureModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() = startKoin {
        androidLogger()
        androidContext(this@MyApplication)
        modules(AppModule().module, InfrastructureModule)
    }
}