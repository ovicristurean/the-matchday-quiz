package com.ovidiucristurean.thematchdayquiz.data.firebase.quiz

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val uid: String,
    val email: String
)
