package com.ovidiucristurean.thematchdayquiz.data.firebase.auth

import dev.gitlive.firebase.auth.AuthResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

sealed class AuthenticationResult {

    data class Success(val data: Any? = null) : AuthenticationResult()

    data class Failure(val error: String) : AuthenticationResult()

    data class Dismissed(val error: String? = null) : AuthenticationResult()
}

fun authenticate(
    authFunction: suspend () -> AuthResult,
    sideEffect: (AuthResult) -> Unit = { }
): Flow<AuthenticationResult> = callbackFlow {
    try {
        withContext(Dispatchers.Default) {
            authFunction().also { authResult ->
                trySend(element = AuthenticationResult.Success()).also { sideEffect(authResult) }
            }
        }
    } catch (exception: Exception) {
        trySend(element = AuthenticationResult.Failure(error = exception.message.orEmpty()))
    }
    awaitClose { }
}
