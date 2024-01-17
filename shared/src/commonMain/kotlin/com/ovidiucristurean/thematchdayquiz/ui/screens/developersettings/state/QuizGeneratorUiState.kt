package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state

data class QuizGeneratorUiState(
    var questions: MutableList<QuestionUiState> = mutableListOf(QuestionUiState())
)
