package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.thematchdayquiz.data.datacreator.FirebaseQuizContentCreator
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Answer
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Question
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuestionModel
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state.QuestionUiState
import kotlinx.coroutines.launch

class DeveloperSettingsViewModel : ScreenModel {

    private var questionUiState = QuestionUiState()

    fun updateQuestionData(
        questionItemType: QuestionItemType,
        value: String,
        index: Int
    ) {
        when (questionItemType) {
            QuestionItemType.QUESTION -> {
                questionUiState = questionUiState.copy(
                    question = value
                )
            }

            QuestionItemType.ANSWER -> {
                questionUiState.answers[index] = value
            }

            QuestionItemType.IMAGE -> {
                questionUiState = questionUiState.copy(
                    imageUrl = value
                )
            }

            QuestionItemType.TIME -> {
                questionUiState = questionUiState.copy(
                    timePerQuestion = value.toIntOrNull() ?: 5
                )
            }
        }
    }

    fun generateFirebaseQuestion() {
        screenModelScope.launch {
            FirebaseQuizContentCreator.generateQuestion(
                questionModel = QuestionModel(
                    imageUrl = questionUiState.imageUrl,
                    question = questionUiState.question,
                    answers = questionUiState.answers.mapIndexed { index, answer ->
                        QuizAnswer(
                            answer = answer,
                            isCorrect = index == 2
                        )
                    },
                    timePerQuestion = questionUiState.timePerQuestion
                )
            )
        }
    }

    fun generateFirebaseQuiz() {
        screenModelScope.launch {
            FirebaseQuizContentCreator.generateQuiz(
                listOf(
                    Question(
                        imageUrl = questionUiState.imageUrl,
                        question = questionUiState.question,
                        answers = questionUiState.answers.mapIndexed { index, answer ->
                            Answer(
                                answer = answer,
                                isCorrect = index == 2
                            )
                        },
                        timePerQuestion = questionUiState.timePerQuestion
                    )
                )
            )
        }
    }
}
