package com.ovidiucristurean.thematchdayquiz.domain.usecase

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService

class RegisterWithEmailAndPasswordUseCase(
    private val authenticationService: AuthenticationService
) {

    operator fun invoke(email: String, password: String) =
        authenticationService.registerWithEmailAndPassWord(email, password)
}
