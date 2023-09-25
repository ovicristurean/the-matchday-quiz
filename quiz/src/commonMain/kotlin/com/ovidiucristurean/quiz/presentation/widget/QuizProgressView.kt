package com.ovidiucristurean.quiz.presentation.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun QuizProgressView(
    currentQuestionNumber: Int,
    totalQuestions: Int,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LinearProgressIndicator(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            progress = (currentQuestionNumber - 1).toFloat() / (totalQuestions - 1),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.onSecondary,
        )
        Text(
            text = "$currentQuestionNumber of $totalQuestions",
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}
