package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.di.createAuthenticationModule
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_di.activityProviderModule
import org.koin.core.module.Module

//fun appModule() = listOf(commonModule)

actual fun createAppModule(): List<Module> = buildList {
    add(activityProviderModule)
    add(commonModule)
    addAll(createAuthenticationModule())
}
