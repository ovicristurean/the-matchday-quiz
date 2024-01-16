package com.ovidiucristurean.thematchdayquiz.data.firebase.quiz

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val imageUrl: String? = null,
    val question: String? = null,
    val answers: List<Answer>? = null,
    val timePerQuestion: Int? = null,
)

@Serializable
data class Answer(
    val answer: String,
    val isCorrect: Boolean
)
