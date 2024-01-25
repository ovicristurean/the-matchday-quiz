package com.ovidiucristurean.thematchdayquiz.data.firebase.quiz

import kotlinx.serialization.Serializable

@Serializable
data class UserQuiz(
    val userId: String,
    val quizId: String,
    val userAnswers: List<Int>? = null
)
