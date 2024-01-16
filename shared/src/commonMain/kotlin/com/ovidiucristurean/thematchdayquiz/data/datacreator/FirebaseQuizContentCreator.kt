package com.ovidiucristurean.thematchdayquiz.data.datacreator

import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Question
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Quiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuestionModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.DocumentReference
import dev.gitlive.firebase.firestore.firestore

class FirebaseQuizContentCreator {

    companion object {
        suspend fun generateQuestion(questionModel: QuestionModel): DocumentReference {
            val firestore = Firebase.firestore

            return firestore.collection("questions")
                .add(
                    QuestionModel.serializer(),
                    questionModel
                )
        }

        suspend fun generateQuiz(questions: List<Question>) {
            val firestore = Firebase.firestore

            var quiz = Quiz(questions = questions)

            val quizzCollection = firestore.collection("quizzes")

            quizzCollection.add(
                Quiz.serializer(),
                quiz
            ).also {
                it.update(
                    "id" to it.id
                )
            }
        }
    }
}
