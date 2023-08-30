package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel
import com.ovidiucristurean.thematchdayquiz.domain.repository.QuizRepository
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    private val quizRepository: QuizRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(QuizScreenUiState())
    val state = _state.asStateFlow()

    init {
        //mocked quiz data
        viewModelScope.launch {
            val currentQuiz = quizRepository.getCurrentQuiz()

            _state.update {
                QuizScreenUiState(
                    numberOfQuestions = currentQuiz.size,
                    quizState = QuizState.IN_PROGRESS,
                )
            }

            playQuiz(currentQuiz)
        }
    }

    fun selectAnswer(answerOptionsState: AnswerOptionsState) {
        _state.update {
            it.copy(
                answerOptionsState = answerOptionsState
            )
        }
    }

    private fun playQuiz(quiz: List<QuizModel>) {
        viewModelScope.launch {
            quiz.forEachIndexed { index, _ ->
                _state.update {
                    it.copy(
                        currentQuestionNumber = index + 1,
                        currentQuestion = quiz[index],
                        answerOptionsState = AnswerOptionsState.NOTHING_SELECTED,
                    )
                }

                startTimer(10)
            }

            _state.update {
                it.copy(
                    quizState = QuizState.FINISHED,
                )
            }
        }
    }

    private suspend fun startTimer(seconds: Int) {
        if (seconds <= 0) {
            _state.update {
                it.copy(
                    timeLeftForQuestion = null
                )
            }
            return
        }

        _state.update {
            it.copy(
                timeLeftForQuestion = seconds
            )
        }
        delay(1000)
        startTimer(seconds - 1)
    }
}
