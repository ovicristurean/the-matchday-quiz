package com.ovidiucristurean.thematchdayquiz.ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.thematchdayquiz.domain.quiz.repository.QuizRepository
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.AvailableQuizState
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.HomeScreenUiState
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.viewdata.UserViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val quizRepository: QuizRepository
) : ScreenModel {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                user = UserViewData(
                    username = "Ovidiu"
                ),
                currentQuiz = AvailableQuizState.QuizAvailable(
                    quizImageUrl = "https://e0.pxfuel.com/wallpapers/528/625/desktop-wallpaper-manchester-city-manchester-city-logo-thumbnail.jpg",
                )
            )
        }

        screenModelScope.launch {
            quizRepository.getAllQuizzes().collect { quizzes ->
                _state.update {
                    it.copy(
                        currentQuiz = AvailableQuizState.QuizAvailable(
                            quizImageUrl = "https://e0.pxfuel.com/wallpapers/528/625/desktop-wallpaper-manchester-city-manchester-city-logo-thumbnail.jpg",
                        ),
                        pastQuizzes = quizzes.drop(1).map { it.quizId }
                    )
                }
            }
        }
    }
}
