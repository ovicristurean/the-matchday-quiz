package com.ovidiucristurean.thematchdayquiz.di

import org.koin.core.module.Module

//fun appModule() = listOf(commonModule)

expect fun createAppModule(): List<Module>