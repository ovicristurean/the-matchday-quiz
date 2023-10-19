package com.ovidiucristurean.thematchdayquiz.domain.repository

import com.ovidiucristurean.thematchdayquiz.data.model.PastQuizResult
import kotlinx.coroutines.flow.Flow

interface LocalQuizRepository {

    val pastQuizzes: Flow<List<PastQuizResult>>

}
