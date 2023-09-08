package com.ovidiucristurean.thematchdayquiz.domain.repository

import com.ovidiucristurean.thematchdayquiz.domain.model.CurrentQuiz

class QuizRepositoryImpl : QuizRepository {

    override suspend fun getCurrentQuiz(): CurrentQuiz {
        return mockedAvailableQuizzes
    }
}
