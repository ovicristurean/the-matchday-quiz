package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

data class QuizScreenUiState(
    val numberOfQuestions: Int? = null,
    val currentQuestionNumber: Int? = null,
    val imageUrl: String? = null,
    val question: String? = null,
    val answerOne: String? = null,
    val answerTwo: String? = null,
    val answerThree: String? = null,
    val answerFour: String? = null,
)
