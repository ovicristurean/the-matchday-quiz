package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import com.ovidiucristurean.thematchdayquiz.domain.model.QuizModel

data class QuizScreenUiState(
    val numberOfQuestions: Int? = null,
    val currentQuestionNumber: Int? = null,
    val currentQuestion: QuizModel? = null,
    val timeLeftForQuestion: Int? = null,
    val answerOptionsState: AnswerOptionsState = AnswerOptionsState.NOTHING_SELECTED,
    val quizState: QuizState = QuizState.NOT_STARTED,
)

enum class QuizState {
    NOT_STARTED,
    IN_PROGRESS,
    FINISHED,
}

enum class AnswerOptionsState {
    NOTHING_SELECTED,
    OPTION_1_SELECTED,
    OPTION_2_SELECTED,
    OPTION_3_SELECTED,
    OPTION_4_SELECTED,
}