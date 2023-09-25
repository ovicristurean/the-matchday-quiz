package com.ovidiucristurean.quiz.domain.repository

import com.ovidiucristurean.quiz.domain.model.CurrentQuiz

class QuizRepositoryImpl : QuizRepository {

    override suspend fun getCurrentQuiz(): CurrentQuiz {
        return mockedAvailableQuizzes
    }
}
