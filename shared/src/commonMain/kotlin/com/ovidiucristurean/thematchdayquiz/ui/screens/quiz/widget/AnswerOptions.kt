package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.thematchdayquiz.domain.model.QuizAnswer
import com.ovidiucristurean.thematchdayquiz.ui.widget.button.MatchdayButton

@Composable
internal fun AnswerOptions(
    answers: List<QuizAnswer>,
    selectedOption: QuizAnswer?,
    onOptionSelected: (QuizAnswer) -> Unit,
    modifier: Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(answers) { answer ->
            MatchdayButton(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                buttonColor = when {
                    (selectedOption == answer && selectedOption.isCorrect) -> Color.Blue
                    (selectedOption == answer && !selectedOption.isCorrect) -> Color.Red
                    else -> MaterialTheme.colorScheme.secondary
                },
                onClick = {
                    onOptionSelected(answer)
                },
                isEnabled = selectedOption == answer || selectedOption == null
            ) {
                Text(
                    text = answer.answer,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}
