package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state

data class QuestionUiState(
    var question: String = "",
    val answers: MutableList<String> = mutableListOf("", "", "", ""),
    var imageUrl: String = "",
    var timePerQuestion: Int = 5
)
