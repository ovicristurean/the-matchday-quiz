package com.ovidiucristurean.thematchdayquiz.data.datacreator

import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

class FirebaseQuizContentCreator {

    companion object {
        suspend fun generateData(quizModel: QuizModel) {
            val firestore = Firebase.firestore

            firestore.collection("questions")
                .add(
                    QuizModel.serializer(),
                    quizModel
                )
        }
    }
}