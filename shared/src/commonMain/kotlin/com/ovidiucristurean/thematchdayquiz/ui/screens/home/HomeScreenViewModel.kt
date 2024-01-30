package com.ovidiucristurean.thematchdayquiz.ui.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.navigator.Navigator
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.UserQuiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.repository.QuizRepository
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.AvailableQuizState
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.HomeScreenUiState
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.viewdata.UserViewData
import com.ovidiucristurean.thematchdayquiz.ui.screens.quiz.QuizScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val quizRepository: QuizRepository
) : ScreenModel {

    private val _state = MutableStateFlow(HomeScreenUiState())
    val state = _state.asStateFlow()

    private var currentQuiz: UserQuiz? = null
    private var pastQuizzes: List<UserQuiz> = emptyList()

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
                currentQuiz = quizzes.firstOrNull()
                pastQuizzes = quizzes.drop(1)

                _state.update {
                    it.copy(
                        currentQuiz = AvailableQuizState.QuizAvailable(
                            quizImageUrl = "https://e0.pxfuel.com/wallpapers/528/625/desktop-wallpaper-manchester-city-manchester-city-logo-thumbnail.jpg",
                        ),
                        pastQuizzes = pastQuizzes.map { it.quizId }
                    )
                }
            }
        }
    }

    fun openQuiz(navigator: Navigator) {
        when (_state.value.currentQuiz) {
            is AvailableQuizState.CurrentQuizNotAvailable -> TODO()
            is AvailableQuizState.QuizAvailable -> {
                this.currentQuiz?.quizId?.let { quizId ->
                    navigator.push(QuizScreen(quizId))
                }
            }
        }
    }
}
