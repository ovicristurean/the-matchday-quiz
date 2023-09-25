package com.ovidiucristurean.quiz.domain.repository

import com.ovidiucristurean.quiz.domain.model.CurrentQuiz


interface QuizRepository {

    suspend fun getCurrentQuiz(): CurrentQuiz
}