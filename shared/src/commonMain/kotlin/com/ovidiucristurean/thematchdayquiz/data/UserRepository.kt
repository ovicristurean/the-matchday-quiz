package com.ovidiucristurean.thematchdayquiz.data

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.domain.repository.UserDataSource
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val authenticationService: AuthenticationService
) : UserDataSource {
    override val authenticationState: Flow<AuthenticationState>
        get() = authenticationService.authenticationState

}
