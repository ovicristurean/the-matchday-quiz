package com.ovidiucristurean.thematchdayquiz.domain.quiz.repository

import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Quiz
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.UserQuiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz
import kotlinx.coroutines.flow.Flow


interface QuizRepository {

    suspend fun getCurrentQuiz(): CurrentQuiz

    fun getAllQuizzes(): Flow<List<UserQuiz>>
}
