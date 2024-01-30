package com.ovidiucristurean.thematchdayquiz.domain.quiz.repository

import com.ovidiucristurean.thematchdayquiz.data.USER_ID_KEY
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Quiz
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.UserQuiz
import com.ovidiucristurean.thematchdayquiz.data.local.KeyValueStorage
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuestionModel
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizAnswer
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuizRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val keyValueStorage: KeyValueStorage
) : QuizRepository {

    override fun getCurrentQuiz(quizId: String): Flow<CurrentQuiz> {
        return firebaseFirestore
            .collection("quizzes")
            .snapshots
            .map {
                it.documents.map {
                    it.data<Quiz>()
                }.findLast {
                    it.id == quizId
                }
            }.map {
                if (it == null) {
                    CurrentQuiz.QuizNotAvailable
                } else {
                    CurrentQuiz.AvailableQuiz(
                        questions = it.questions.map {
                            QuestionModel(
                                imageUrl = it.imageUrl,
                                question = it.question,
                                answers = it.answers?.map {
                                    QuizAnswer(
                                        answer = it.answer,
                                        isCorrect = it.isCorrect
                                    )
                                },
                                timePerQuestion = it.timePerQuestion
                            )
                        }
                    )
                }
            }
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
