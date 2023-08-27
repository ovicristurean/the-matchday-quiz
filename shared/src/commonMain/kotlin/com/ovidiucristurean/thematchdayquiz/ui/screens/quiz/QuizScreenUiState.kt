package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel

data class QuizScreenUiState(
    val numberOfQuestions: Int? = null,
    val currentQuestionNumber: Int? = null,
    val currentQuestion: QuizModel? = null,
    val timeLeftForQuestion: Int? = null,
)
