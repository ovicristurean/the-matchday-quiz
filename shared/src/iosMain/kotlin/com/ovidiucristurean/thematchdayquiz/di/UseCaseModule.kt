package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.domain.usecase.ShowErrorMessageUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun createUseCaseModule(): Module = module {
    single { ShowErrorMessageUseCase() }
}