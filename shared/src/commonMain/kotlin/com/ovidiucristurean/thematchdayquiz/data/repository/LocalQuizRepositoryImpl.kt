package com.ovidiucristurean.thematchdayquiz.data.repository

import com.ovidiucristurean.thematchdayquiz.data.model.PastQuizResult
import com.ovidiucristurean.thematchdayquiz.database.MatchdayDatabase
import com.ovidiucristurean.thematchdayquiz.domain.repository.LocalQuizRepository
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import database.Past_quiz_result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalQuizRepositoryImpl(
    private val db: MatchdayDatabase
) : LocalQuizRepository {
    override val pastQuizzes: Flow<List<PastQuizResult>>
        get() = db.matchdayQueries.getPastResults().asFlow().mapToList().map {

        }
}

private fun List<Past_quiz_result>.toList(): List<PastQuizResult> {
    return map {
        PastQuizResult(
            id = it.id.toInt(),
            quizQuestions = it,
        )
    }
}
