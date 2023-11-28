package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.di

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api.AndroidAuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.implementation.AndroidAuthenticationServiceImpl
import com.ovidiucristurean.thematchdayquiz.data.firebase.implementation.AuthenticationServiceImpl
import com.ovidiucristurean.thematchdayquiz.domain.auth.GetIntentForGoogleAccountLoginUseCase
import com.ovidiucristurean.thematchdayquiz.domain.auth.LoginWithGoogleAccountUseCase
import com.ovidiucristurean.thematchdayquiz.ui.screens.auth.AuthScreenState
import org.koin.core.module.Module
import org.koin.dsl.module


actual fun createAuthenticationModule(): List<Module> = buildList { add(element = module {
        factory {
            GetIntentForGoogleAccountLoginUseCase(service = get() as AndroidAuthenticationService)
        }

        factory {
            LoginWithGoogleAccountUseCase(get() as AndroidAuthenticationService)
        }

        factory {
            AuthScreenState(get(), get())
        }
    })
    add(element = module { single<AuthenticationService> { AuthenticationServiceImpl() } })
    add(element = module { single { AndroidAuthenticationServiceImpl() } })
}
