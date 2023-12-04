package com.ovidiucristurean.thematchdayquiz.di

import com.ovidiucristurean.thematchdayquiz.data.UserRepository
import com.ovidiucristurean.thematchdayquiz.domain.repository.UserDataSource
import com.ovidiucristurean.thematchdayquiz.domain.usecase.GetAuthenticationStateUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.LoginWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.LogoutUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.RegisterWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.ui.screens.profile.UserProfileViewModel
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.RegisterViewModel
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.SignInViewModel
import com.ovidiucristurean.thematchdayquiz.ui.util.viewModelDefinition
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.dsl.module

val commonModule = module {
    single { Firebase.auth }
    single<UserDataSource> { UserRepository(get()) }
    factory { GetAuthenticationStateUseCase(get()) }
    factory { LoginWithEmailAndPasswordUseCase(get()) }
    viewModelDefinition { SignInViewModel(get()) }

    factory { RegisterWithEmailAndPasswordUseCase(get()) }
    factory { RegisterViewModel(get(), get()) }

    factory { LogoutUseCase(get()) }

    viewModelDefinition { UserProfileViewModel(get()) }
}
