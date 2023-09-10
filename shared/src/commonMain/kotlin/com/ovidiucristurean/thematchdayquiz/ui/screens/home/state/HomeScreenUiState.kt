package com.ovidiucristurean.thematchdayquiz.ui.screens.home.state

import com.ovidiucristurean.thematchdayquiz.ui.screens.home.state.viewdata.UserViewData

data class HomeScreenUiState(
    val user: UserViewData? = null,
    val currentQuiz: AvailableQuizState = AvailableQuizState.CurrentQuizNotAvailable(1000L),
)

sealed class AvailableQuizState {

    data class CurrentQuizNotAvailable(
        val availableTime: Long,
    ) : AvailableQuizState()

    data class QuizAvailable(
        val quizImageUrl: String,
        val numberOfQuestions: Int,
    ) : AvailableQuizState()
}
