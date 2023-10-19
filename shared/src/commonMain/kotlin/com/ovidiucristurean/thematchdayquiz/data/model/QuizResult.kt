package com.ovidiucristurean.thematchdayquiz.data.model

data class QuizResult(
    val id: Int,
    val quizQuestions: List<QuizQuestion>
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val answers: List<QuizAnswer>
)
