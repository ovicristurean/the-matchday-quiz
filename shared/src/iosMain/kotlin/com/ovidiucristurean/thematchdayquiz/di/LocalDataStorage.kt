package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.local.KeyValueStorage
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun createLocalDataStorageModule(): Module =
    module {
        single { KeyValueStorage() }
    }