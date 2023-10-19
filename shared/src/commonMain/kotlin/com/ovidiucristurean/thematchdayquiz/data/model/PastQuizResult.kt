package com.ovidiucristurean.thematchdayquiz.data.model

data class PastQuizResult(
    val id: Int,
    val quizQuestions: List<QuizQuestion>
)

data class PastQuizQuestion(
    val id: Int,
    val question: String,
    val answers: List<QuizAnswer>,
    val chosenAnswer: QuizAnswer
)

