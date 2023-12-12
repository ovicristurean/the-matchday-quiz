package com.ovidiucristurean.thematchdayquiz.domain.quiz.repository

import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz


interface QuizRepository {

    suspend fun getCurrentQuiz(): CurrentQuiz
}