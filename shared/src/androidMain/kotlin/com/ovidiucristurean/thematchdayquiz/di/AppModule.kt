package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.repository.LocalQuizRepositoryImpl
import com.ovidiucristurean.thematchdayquiz.domain.repository.LocalQuizRepository

actual class AppModule {
    actual val localQuizRepository: LocalQuizRepository
        get() = LocalQuizRepositoryImpl()
}