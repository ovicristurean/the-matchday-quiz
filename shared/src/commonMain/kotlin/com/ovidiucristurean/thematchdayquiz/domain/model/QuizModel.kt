package com.ovidiucristurean.thematchdayquiz.domain.model

data class QuizModel(
    val imageUrl: String? = null,
    val question: String? = null,
    val answers: List<QuizAnswer>? = null,
)

data class QuizAnswer(
    val answer: String,
    val isCorrect: Boolean
)
