package com.ovidiucristurean.thematchdayquiz.di

import org.koin.core.module.Module

//fun appModule() = listOf(commonModule)

actual fun createAppModule(): List<Module> = buildList {
    add(commonModule)
}