package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationResult
import com.ovidiucristurean.thematchdayquiz.domain.usecase.LoginWithEmailAndPasswordUseCase
import com.ovidiucristurean.thematchdayquiz.domain.usecase.ShowErrorMessageUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SignInViewModel(
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase,
    private val showErrorMessageUseCase: ShowErrorMessageUseCase
) : ViewModel() {

    private val authResultUiStateChannel = Channel<Unit>()
    val userSignedInEvent = authResultUiStateChannel.receiveAsFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginWithEmailAndPasswordUseCase.invoke(username, password).onEach {
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
