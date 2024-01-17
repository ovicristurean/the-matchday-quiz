package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.thematchdayquiz.data.datacreator.FirebaseQuizContentCreator
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Answer
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.Question
import com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state.QuestionUiState
import com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state.QuizGeneratorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeveloperSettingsViewModel : ScreenModel {

    private var _quizUiState = MutableStateFlow(QuizGeneratorUiState())
    val quizUiState = _quizUiState.asStateFlow()

    fun updateQuestionData(
        quizQuestionIndex: Int,
        questionItemType: QuestionItemType,
        value: String,
        answerIndex: Int
    ) {
        when (questionItemType) {
            QuestionItemType.QUESTION -> {
                _quizUiState.update {
                    it.questions[quizQuestionIndex].question = value
                    it
                }
            }

            QuestionItemType.ANSWER -> {
                _quizUiState.update {
                    it.questions[quizQuestionIndex].answers[answerIndex] = value
                    it
                }
            }

            QuestionItemType.IMAGE -> {
                _quizUiState.update {
                    it.questions[quizQuestionIndex].imageUrl = value
                    it
                }
            }

            QuestionItemType.TIME -> {
                _quizUiState.update {
                    it.questions[quizQuestionIndex].timePerQuestion =
                        value.toIntOrNull() ?: 5
                    it
                }
            }
        }
    }

    fun generateFirebaseQuiz() {
        screenModelScope.launch {
            FirebaseQuizContentCreator.generateQuiz(
                _quizUiState.value.questions.map {
                    Question(
                        imageUrl = it.imageUrl,
                        question = it.question,
                        answers = it.answers.mapIndexed { index, answer ->
                            Answer(
                                answer = answer,
                                isCorrect = index == 2
                            )
                        },
                        timePerQuestion = it.timePerQuestion
                    )
                }
            )
        }
    }

    fun addQuestionToQuiz() {
        _quizUiState.update { currentQuizState ->
            currentQuizState.copy(
                questions = currentQuizState.questions.toMutableList().apply {
                    add(QuestionUiState())
                }
            )
        }
    }
}
