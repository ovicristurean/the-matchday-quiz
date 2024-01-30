package com.ovidiucristurean.thematchdayquiz.domain.quiz.repository

import com.ovidiucristurean.thematchdayquiz.data.USER_ID_KEY
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.UserQuiz
import com.ovidiucristurean.thematchdayquiz.data.local.KeyValueStorage
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val keyValueStorage: KeyValueStorage
) : QuizRepository {

    override suspend fun getCurrentQuiz(): CurrentQuiz {
        return mockedAvailableQuizzes
    }

    override fun getAllQuizzes(): Flow<List<UserQuiz>> {
        return firebaseFirestore
            .collection("userQuizzes")
            .snapshots()
            .map {
                it.documents.map {
                    it.data<UserQuiz>()
                }.filter {
                    it.userId == keyValueStorage.settings.getString(USER_ID_KEY, "")
                }
            }
    }
}
