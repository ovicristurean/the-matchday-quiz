package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.UserRepository
import com.ovidiucristurean.thematchdayquiz.domain.repository.UserDataSource
import com.ovidiucristurean.thematchdayquiz.domain.usecase.GetAuthenticationStateUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.LoginWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.SignInViewModel
import com.ovidiucristurean.thematchdayquiz.ui.util.viewModelDefinition
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.dsl.module

val commonModule = module {
    single { Firebase.firestore }
    single { Firebase.auth }
    single<UserDataSource> { UserRepository(get()) }
    factory { GetAuthenticationStateUseCase(get()) }
    factory { LoginWithEmailAndPasswordUseCase(get()) }
    viewModelDefinition { SignInViewModel(get()) }
}
