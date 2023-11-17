package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.domain.usecase.LoginWithEmailAndPasswordUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignInViewModel(
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase
) : ViewModel() {

    var authState = MutableStateFlow(LoginResult.IDLE)
        private set

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginWithEmailAndPasswordUseCase.invoke(username, password).onEach {
                authState.value = when (it) {
                    is AuthResult.Success -> {
                        LoginResult.LOGGED
                    }

                    is AuthResult.Failure -> {
                        LoginResult.NOT_LOGGED
                    }

                    is AuthResult.Dismissed -> {
                        LoginResult.NOT_LOGGED
                    }
                }
            }.stateIn(this)
        }
    }

}

enum class LoginResult {
    IDLE,
    NOT_LOGGED,
    LOGGED
}

