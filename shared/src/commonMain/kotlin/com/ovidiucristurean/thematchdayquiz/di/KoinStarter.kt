package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.di.createAuthenticationModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(createAuthenticationModule() + commonModule + createUseCaseModule())
    }
}
