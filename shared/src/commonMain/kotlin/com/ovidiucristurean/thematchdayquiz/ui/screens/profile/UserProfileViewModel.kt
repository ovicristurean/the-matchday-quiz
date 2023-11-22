package com.ovidiucristurean.thematchdayquiz.ui.screens.profile

import com.ovidiucristurean.thematchdayquiz.domain.usecase.LogoutUseCase
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class UserProfileViewModel(
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.invoke()
        }
    }
}
