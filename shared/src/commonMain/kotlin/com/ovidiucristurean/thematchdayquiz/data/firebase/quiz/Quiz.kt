package com.ovidiucristurean.thematchdayquiz.data.firebase.quiz

import kotlinx.serialization.Serializable

@Serializable
data class Quiz(
    val id: String? = null,
    val questions: List<Question>
)
