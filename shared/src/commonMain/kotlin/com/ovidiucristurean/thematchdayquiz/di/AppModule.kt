package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.domain.repository.LocalQuizRepository

expect class AppModule {
    val localQuizRepository:LocalQuizRepository
}