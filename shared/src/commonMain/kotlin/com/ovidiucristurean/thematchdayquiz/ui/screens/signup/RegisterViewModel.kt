package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.domain.usecase.RegisterWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.ShowErrorMessageUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val registerWithEmailAndPasswordUseCase: RegisterWithEmailAndPasswordUseCase,
    private val showErrorMessageUseCase: ShowErrorMessageUseCase
) : ScreenModel {

    private val authResultUiStateChannel = Channel<Unit>()
    val authResultEvent = authResultUiStateChannel.receiveAsFlow()

    fun register(email: String, password: String) {
        coroutineScope.launch {
            registerWithEmailAndPasswordUseCase.invoke(email, password).onEach {
                when (it) {
                    is AuthResult.Success -> {
                        authResultUiStateChannel.trySend(Unit)
                    }

                    is AuthResult.Failure -> {
                        showErrorMessage(it.error)
                    }

                    is AuthResult.Dismissed -> {
                        showErrorMessage(it.error ?: "")
                    }
                }
            }.stateIn(this)
        }
    }

    private fun showErrorMessage(message: String) {
        showErrorMessageUseCase.showErrorMessage(message)
    }
}
