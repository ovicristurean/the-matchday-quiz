package com.ovidiucristurean.thematchdayquiz

import android.app.Application
import com.ovidiucristurean.thematchdayquiz.di.createAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MatchdayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MatchdayApplication)
            modules(
                modules = createAppModule()
            )
        }
    }
}