package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationResult
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
        screenModelScope.launch {
            registerWithEmailAndPasswordUseCase.invoke(email, password).onEach {
                when (it) {
                    is AuthenticationResult.Success -> {
                        authResultUiStateChannel.trySend(Unit)
                    }

                    is AuthenticationResult.Failure -> {
                        showErrorMessage(it.error)
                    }

                    is AuthenticationResult.Dismissed -> {
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
