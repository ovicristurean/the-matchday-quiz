package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel
import com.ovidiucristurean.thematchdayquiz.domain.repository.QuizRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<QuizScreenUiState> =
        MutableStateFlow(QuizScreenUiState.QuizNotStarted)
    val state: StateFlow<QuizScreenUiState> = _state.asStateFlow()

    var quizInProgressState = QuizScreenUiState.QuizScreenInProgress()

    init {
        //mocked quiz data
        viewModelScope.launch {
            val currentQuiz = quizRepository.getCurrentQuiz()

            quizInProgressState = QuizScreenUiState.QuizScreenInProgress(
                numberOfQuestions = currentQuiz.size,
                currentQuestionNumber = 1,
                currentQuestion = currentQuiz[0],
                timeLeftForQuestion = QUIZ_TIME,
            )
            _state.update {
                quizInProgressState
            }

            playQuiz(currentQuiz)
        }
    }

    fun selectAnswer(answer:QuizAnswer) {
        quizInProgressState = quizInProgressState.copy(
            selectedAnswer = answer
        )
        _state.update {
            quizInProgressState
        }
    }

    private fun playQuiz(quiz: List<QuizModel>) {
        viewModelScope.launch {
            quiz.forEachIndexed { index, _ ->
                quizInProgressState = quizInProgressState.copy(
                    currentQuestionNumber = index + 1,
                    currentQuestion = quiz[index],
                    selectedAnswer = null,
                )
                _state.update {
                    quizInProgressState
                }

                startTimer(QUIZ_TIME)
            }

            _state.update {
                QuizScreenUiState.QuizFinished
            }
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

    private companion object {
        const val QUIZ_TIME = 5
    }
}
