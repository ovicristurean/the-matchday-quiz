package com.ovidiucristurean.thematchdayquiz.domain.quiz.model

import kotlinx.serialization.Serializable

@Serializable
data class QuestionModel(
    val imageUrl: String? = null,
    val question: String? = null,
    val answers: List<QuizAnswer>? = null,
    val timePerQuestion: Int? = null,
)

@Serializable
data class QuizAnswer(
    val answer: String,
    val isCorrect: Boolean
)
