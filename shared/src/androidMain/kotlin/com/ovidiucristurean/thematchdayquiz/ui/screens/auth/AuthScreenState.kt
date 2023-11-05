package com.ovidiucristurean.thematchdayquiz.ui.screens.auth

import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.domain.auth.GetIntentForGoogleAccountLoginUseCase
import com.ovidiucristurean.thematchdayquiz.domain.auth.LoginWithGoogleAccountUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthScreenState(
    private val getIntentForGoogleAccountLogin: GetIntentForGoogleAccountLoginUseCase,
    private val loginWithGoogleAccountUseCase: LoginWithGoogleAccountUseCase,
) : ScreenModel {
    var state by mutableStateOf<State>(value = State.Normal)

    val intentForGoogleLogin: Intent = getIntentForGoogleAccountLogin()

    fun authenticateWithGoogle(intent: Intent) {
        state = State.Loading
        coroutineScope.launch {
            loginWithGoogleAccountUseCase(intent = intent).onEach { result ->
                state = when (result) {
                    is AuthResult.Failure -> State.Error(message = result.error)
                    is AuthResult.Success, is AuthResult.Dismissed -> State.Normal
                }
            }.stateIn(scope = this)
        }
    }
}

sealed class State {
    object Normal : State()
    object Loading : State()
    data class Error(val message: String) : State()
}
