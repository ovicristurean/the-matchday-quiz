package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.QuestionItemType

@Composable
fun QuizItem(
    onQuizItemChanged: (QuestionItemType, String, Int) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        QuestionItemField(
            itemTitle = "Question:",
            onValueChanged = { value, index ->
                onQuizItemChanged(QuestionItemType.QUESTION, value, index)
            }
        )

        QuestionItemField(
            itemTitle = "Image URL:",
            onValueChanged = { value, index ->
                onQuizItemChanged(QuestionItemType.IMAGE, value, index)
            }
        )

        QuestionItemField(
            itemTitle = "Answers:",
            //TODO make the number of answers more configurable in the future
            numberOfValues = 4,
            onValueChanged = { value, index ->
                onQuizItemChanged(QuestionItemType.ANSWER, value, index)
            }
        )

        QuestionItemField(
            itemTitle = "Time per question (seconds):",
            onValueChanged = { value, index ->
                onQuizItemChanged(QuestionItemType.TIME, value, index)
            }
        )
    }
}

@Composable
private fun QuestionItemField(
    itemTitle: String,
    numberOfValues: Int = 1,
    onValueChanged: (String, Int) -> Unit
) {
    Text(
        text = itemTitle,
        style = TextStyle(
            color = MaterialTheme.colorScheme.onBackground
        )
    )

    for (i in 0 until numberOfValues) {
        AnswerTextField(
            answerIndex = i,
            onValueChanged = onValueChanged
        )
    }
}

@Composable
private fun AnswerTextField(
    answerIndex: Int,
    onValueChanged: (String, Int) -> Unit
) {
    var questionValue by rememberSaveable {
        mutableStateOf("")
    }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = if (answerIndex > 0) 10.dp else 0.dp),
        value = questionValue,
        onValueChange = { newValue ->
            questionValue = newValue
            onValueChanged(newValue, answerIndex)
        }
    )
}