package com.ovidiucristurean.thematchdayquiz.ui.screens.home

import com.ovidiucristurean.thematchdayquiz.ui.state.HomeScreenUiState
import com.ovidiucristurean.thematchdayquiz.ui.state.viewdata.UserViewData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    init {
        //populate HomeScreenUiState with mocked data
        _state.update {
            it.copy(
                user = UserViewData(
                    username = "Ovidiu"
                )
            )
        }
    }
}
