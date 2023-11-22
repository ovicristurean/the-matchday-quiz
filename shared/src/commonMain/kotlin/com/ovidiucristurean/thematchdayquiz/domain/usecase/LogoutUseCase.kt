package com.ovidiucristurean.thematchdayquiz.domain.usecase

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService

class LogoutUseCase(
    private val authenticationService: AuthenticationService
) {

    suspend fun invoke() {
        authenticationService.logOut()
    }
}