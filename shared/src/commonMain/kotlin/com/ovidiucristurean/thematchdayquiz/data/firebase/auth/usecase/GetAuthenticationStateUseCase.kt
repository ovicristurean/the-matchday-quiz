package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.usecase

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import kotlinx.coroutines.flow.filterNotNull

class GetAuthenticationStateUseCase(
    private val authenticationService: AuthenticationService
) {

    operator fun invoke() = authenticationService.authenticationState.filterNotNull()
}
