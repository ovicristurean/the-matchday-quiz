package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.domain.usecase.RegisterWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.ShowErrorMessageUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPasswordUseCase,
    private val showErrorMessageUseCase: ShowErrorMessageUseCase
) : ViewModel() {

    var authState = MutableStateFlow(LoginResult.IDLE)
        private set

    fun register(email: String, password: String) {
        viewModelScope.launch {
            registerWithEmailAndPasswordUseCase.invoke(email, password).onEach {
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

    fun showErrorMessage(message: String) {
        showErrorMessageUseCase.showErrorMessage(message)
    }
}
