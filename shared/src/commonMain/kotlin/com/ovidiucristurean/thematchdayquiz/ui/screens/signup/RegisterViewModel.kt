package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.domain.usecase.RegisterWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.ShowErrorMessageUseCase
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPasswordUseCase,
    private val showErrorMessageUseCase: ShowErrorMessageUseCase
) : StateScreenModel<LoginResult>(LoginResult.IDLE) {

    fun register(email: String, password: String) {
        coroutineScope.launch {
            registerWithEmailAndPasswordUseCase.invoke(email, password).onEach {
                mutableState.value = when (it) {
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
