package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuestionModel


sealed interface QuizScreenUiState {

    object QuizNotStarted : QuizScreenUiState

    data class QuizScreenInProgress(
        val numberOfQuestions: Int? = null,
        val currentQuestionNumber: Int? = null,
        val currentQuestion: QuestionModel? = null,
        val timeLeftForQuestion: Int? = null,
        val selectedAnswer: QuizAnswer? = null,
        val totalScore: Int = 0
    ) : QuizScreenUiState

    data class QuizFinished(
        val result: QuizResult
    ) : QuizScreenUiState
}

data class QuizResult(
    val numberOfPoints: Int
)
