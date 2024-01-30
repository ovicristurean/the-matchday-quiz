package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.CurrentQuiz
import com.ovidiucristurean.thematchdayquiz.domain.quiz.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.domain.quiz.repository.QuizRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
) : ScreenModel {

    private val _state: MutableStateFlow<QuizScreenUiState> =
        MutableStateFlow(QuizScreenUiState.QuizNotStarted)
    val state: StateFlow<QuizScreenUiState> = _state.asStateFlow()

    private var quizInProgressState = QuizScreenUiState.QuizScreenInProgress()
    private var answeredQuestionsSet = mutableSetOf<QuizAnswer>()

    fun fetchCurrentQuiz(quizId: String) {
        screenModelScope.launch {
            quizRepository.getCurrentQuiz(quizId).collectLatest { currentQuiz ->
                when (currentQuiz) {
                    is CurrentQuiz.QuizNotReady -> {

                    }

                    is CurrentQuiz.QuizNotAvailable -> {

                    }

                    is CurrentQuiz.AvailableQuiz -> {
                        quizInProgressState = QuizScreenUiState.QuizScreenInProgress(
                            numberOfQuestions = currentQuiz.questions.size,
                            currentQuestionNumber = 1,
                            currentQuestion = currentQuiz.questions.firstOrNull(),
                            timeLeftForQuestion = currentQuiz.questions.firstOrNull()?.timePerQuestion,
                        )
                        _state.update {
                            quizInProgressState
                        }

                        playQuiz(currentQuiz)
                    }
                }
            }
        }
    }

    fun selectAnswer(answer: QuizAnswer) {
        if (!answeredQuestionsSet.contains(answer)) {
            quizInProgressState = quizInProgressState.copy(
                selectedAnswer = answer,
                totalScore = if (answer.isCorrect) quizInProgressState.totalScore + 1 else quizInProgressState.totalScore
            )
            _state.update {
                quizInProgressState
            }
            answeredQuestionsSet.add(answer)
        }
    }

    private fun playQuiz(quiz: CurrentQuiz.AvailableQuiz) {
        screenModelScope.launch {
            quiz.questions.forEachIndexed { index, _ ->
                quizInProgressState = quizInProgressState.copy(
                    currentQuestionNumber = index + 1,
                    currentQuestion = quiz.questions[index],
                    selectedAnswer = null,
                )
                _state.update {
                    quizInProgressState
                }

                startTimer(quiz.questions[index].timePerQuestion ?: 0)
            }

            finishGame()
        }
    }

    private suspend fun startTimer(seconds: Int) {
        if (seconds <= 0) {
            quizInProgressState = quizInProgressState.copy(
                timeLeftForQuestion = null
            )
            _state.update {
                quizInProgressState
            }
            return
        }

        _state.update {
            quizInProgressState = quizInProgressState.copy(
                timeLeftForQuestion = seconds
            )
            quizInProgressState
        }
        delay(1000)
        startTimer(seconds - 1)
    }

    private fun finishGame() {
        _state.update {
            QuizScreenUiState.QuizFinished(
                QuizResult(
                    numberOfPoints = quizInProgressState.totalScore
                )
            )
        }

        quizInProgressState = QuizScreenUiState.QuizScreenInProgress()
        answeredQuestionsSet.clear()
    }
}
