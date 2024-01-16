package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ovidiucristurean.thematchdayquiz.ui.screens.getScreenModel

class DeveloperSettingsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<DeveloperSettingsViewModel>()

        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Generate mock firebase question:",
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }

                item {
                    QuestionItemField(
                        itemTitle = "Question:",
                        onValueChanged = { value, index ->
                            viewModel.updateQuestionData(
                                QuestionItemType.QUESTION,
                                value,
                                index
                            )
                        }
                    )
                }

                item {
                    QuestionItemField(
                        itemTitle = "Image URL:",
                        onValueChanged = { value, index ->
                            viewModel.updateQuestionData(
                                QuestionItemType.IMAGE,
                                value,
                                index
                            )
                        }
                    )
                }

                item {
                    QuestionItemField(
                        itemTitle = "Answers:",
                        numberOfValues = 4,
                        onValueChanged = { value, index ->
                            viewModel.updateQuestionData(
                                QuestionItemType.ANSWER,
                                value,
                                index
                            )
                        }
                    )
                }

                item {
                    QuestionItemField(
                        itemTitle = "Time per question (seconds):",
                        onValueChanged = { value, index ->
                            viewModel.updateQuestionData(
                                QuestionItemType.TIME,
                                value,
                                index
                            )
                        }
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                viewModel.generateFirebaseQuestion()
                            }
                        ) {
                            Text(text = "Upload question")
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                viewModel.generateFirebaseQuiz()
                            }
                        ) {
                            Text(text = "Upload quiz")
                        }
                    }
                }
            }
        }
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

enum class QuestionItemType {
    QUESTION,
    ANSWER,
    IMAGE,
    TIME
}
