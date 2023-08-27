package com.ovidiucristurean.thematchdayquiz.domain.repository

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel

interface QuizRepository {

    suspend fun getCurrentQuiz():List<QuizModel>
}