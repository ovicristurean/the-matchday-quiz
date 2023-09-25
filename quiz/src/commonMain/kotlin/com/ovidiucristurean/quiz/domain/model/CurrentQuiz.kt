package com.ovidiucristurean.quiz.domain.model

sealed class CurrentQuiz {

    object QuizNotAvailable : CurrentQuiz()

    data class QuizNotReady(
        val timeUntilAvailable: Long,
    ) : CurrentQuiz()

    data class AvailableQuiz(
        val questions: List<QuizModel>,
    ) : CurrentQuiz()
}
