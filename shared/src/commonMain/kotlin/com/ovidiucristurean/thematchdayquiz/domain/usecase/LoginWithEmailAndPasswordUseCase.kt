package com.ovidiucristurean.thematchdayquiz.domain.usecase

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

class LoginWithEmailAndPasswordUseCase(
    private val authenticationService: AuthenticationService
) {

    operator fun invoke(email: String, password: String): Flow<AuthResult> =
        authenticationService.loginWithEmailAndPassword(email = email, password = password)
            .filterNotNull()
}
