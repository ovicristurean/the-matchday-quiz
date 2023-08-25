package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuizViewModel : ViewModel() {

    private val _state = MutableStateFlow(QuizScreenUiState())
    val state = _state.asStateFlow()

    init {
        //mocked quiz data
        _state.update {
            QuizScreenUiState(
                numberOfQuestions = 12,
                currentQuestionNumber = 8,
                imageUrl = "https://loremflickr.com/cache/resized/1712_25859505144_682b79f747_n_320_240_nofilter.jpg",
                question = "question one",
                answerOne = "answer one",
                answerTwo = "answer two",
                answerThree = "answer three",
                answerFour = "answer four",
            )
        }
    }
}
