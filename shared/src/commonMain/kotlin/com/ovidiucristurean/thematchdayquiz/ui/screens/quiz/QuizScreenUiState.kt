package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel

sealed interface QuizScreenUiState {

    object QuizNotStarted : QuizScreenUiState

    data class QuizScreenInProgress(
        val numberOfQuestions: Int? = null,
        val currentQuestionNumber: Int? = null,
        val currentQuestion: QuizModel? = null,
        val timeLeftForQuestion: Int? = null,
        val selectedAnswer: QuizAnswer? = null,
        val totalScore: Int = 0
    ) : QuizScreenUiState

    object QuizFinished : QuizScreenUiState
}