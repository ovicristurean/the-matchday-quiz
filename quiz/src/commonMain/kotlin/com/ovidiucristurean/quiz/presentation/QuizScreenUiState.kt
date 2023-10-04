package com.ovidiucristurean.quiz.presentation

import com.ovidiucristurean.quiz.domain.model.QuizAnswer
import com.ovidiucristurean.quiz.domain.model.QuizModel


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

    data class QuizFinished(
        val result: QuizResult
    ) : QuizScreenUiState
}

data class QuizResult(
    val numberOfPoints: Int
)
