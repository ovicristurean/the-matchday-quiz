package com.ovidiucristurean.thematchdayquiz.domain.repository

import com.ovidiucristurean.thematchdayquiz.domain.model.CurrentQuiz

interface QuizRepository {

    suspend fun getCurrentQuiz(): CurrentQuiz
}