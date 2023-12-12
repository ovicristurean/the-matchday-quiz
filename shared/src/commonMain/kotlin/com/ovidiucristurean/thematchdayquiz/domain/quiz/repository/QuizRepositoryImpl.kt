package com.ovidiucristurean.thematchdayquiz.domain.quiz.repository

import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.repository.QuizRepository
import com.ovidiucristurean.thematchdayquiz.domain.quiz.repository.mockedAvailableQuizzes

class QuizRepositoryImpl : QuizRepository {

    override suspend fun getCurrentQuiz(): CurrentQuiz {
        return mockedAvailableQuizzes
    }
}
