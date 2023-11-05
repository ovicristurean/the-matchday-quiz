package com.ovidiucristurean.thematchdayquiz.domain.repository

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    val authenticationState: Flow<AuthenticationState>
}
