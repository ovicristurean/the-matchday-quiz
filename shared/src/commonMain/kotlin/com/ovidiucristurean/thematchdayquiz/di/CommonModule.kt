package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.UserRepository
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.usecase.GetAuthenticationStateUseCase
import com.ovidiucristurean.thematchdayquiz.domain.repository.UserDataSource
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val commonModule = module {
    single { Firebase.firestore }
    single { Firebase.auth }
    single<UserDataSource> { UserRepository(get()) }
    factory { GetAuthenticationStateUseCase(get()) }
}
