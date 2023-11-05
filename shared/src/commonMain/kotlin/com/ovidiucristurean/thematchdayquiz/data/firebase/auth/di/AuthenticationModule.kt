package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.di

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.implementation.AuthenticationServiceImpl
import org.koin.core.module.Module
import org.koin.dsl.module


expect fun createAuthenticationModule(): List<Module>

val commonModule: Module = module { single<AuthenticationService> { AuthenticationServiceImpl() } }