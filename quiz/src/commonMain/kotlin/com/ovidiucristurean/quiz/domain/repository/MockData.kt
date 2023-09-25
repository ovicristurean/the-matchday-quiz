package com.ovidiucristurean.quiz.domain.repository

import com.ovidiucristurean.quiz.domain.model.CurrentQuiz
import com.ovidiucristurean.quiz.domain.model.QuizAnswer
import com.ovidiucristurean.quiz.domain.model.QuizModel

val mockedAvailableQuizzes = CurrentQuiz.AvailableQuiz(mutableListOf<QuizModel>().apply {
    (1..12).forEach {
        add(
            QuizModel(
                imageUrl = "https://loremflickr.com/cache/resized/1712_25859505144_682b79f747_n_320_240_nofilter.jpg",
                question = "question $it",
                answers = listOf(
                    QuizAnswer(
                        answer = "answer one for question $it", isCorrect = false
                    ), QuizAnswer(
                        answer = "answer two for question $it", isCorrect = false
                    ), QuizAnswer(
                        answer = "answer three for question $it",
                        isCorrect = true,
                    ), QuizAnswer(
                        answer = "answer four for question $it", isCorrect = false
                    )
                ),
                timePerQuestion = 5,
            )
        )
    }
})
