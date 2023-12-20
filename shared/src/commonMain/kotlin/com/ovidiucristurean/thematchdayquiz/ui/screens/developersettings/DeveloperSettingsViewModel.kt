package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.ovidiucristurean.thematchdayquiz.data.datacreator.FirebaseQuizContentCreator
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizModel
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
        coroutineScope.launch {
            FirebaseQuizContentCreator.generateData(
                quizModel = QuizModel(
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
}
