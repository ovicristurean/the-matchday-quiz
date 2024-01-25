package com.ovidiucristurean.thematchdayquiz.data.firebase.auth

import kotlinx.coroutines.flow.Flow

sealed class AuthenticationState {
    object Idle : AuthenticationState()
    object Logged : AuthenticationState()
    object NotLogged : AuthenticationState()
}

interface AuthenticationService {

    val authenticationState: Flow<AuthenticationState>

    fun loginWithEmailAndPassword(email: String, password: String): Flow<AuthenticationResult>

    fun registerWithEmailAndPassWord(email: String, password: String): Flow<AuthenticationResult>

    suspend fun logOut()

    suspend fun resetPassword(email: String)
}
