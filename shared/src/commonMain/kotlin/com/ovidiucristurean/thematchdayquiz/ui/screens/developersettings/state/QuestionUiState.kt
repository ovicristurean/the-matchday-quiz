package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.state

data class QuestionUiState(
    val question: String = "",
    val answers: MutableList<String> = mutableListOf("", "", "", ""),
    val imageUrl: String = "",
    val timePerQuestion: Int = 5
)
