package com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.ovidiucristurean.thematchdayquiz.ui.screens.getScreenModel
import com.ovidiucristurean.thematchdayquiz.ui.screens.quiz.widget.QuizItem

class DeveloperSettingsScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<DeveloperSettingsViewModel>()
        val quizUiState by viewModel.quizUiState.collectAsState()

        Scaffold(
            contentColor = MaterialTheme.colorScheme.background,
            floatingActionButton = {
                FloatingActionButton(
                    shape = RoundedCornerShape(100.dp),
                    onClick = {
                        viewModel.addQuestionToQuiz()
                    }) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Add"
                    )
                }
            }
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

                items(quizUiState.questions.size) {
                    QuizItem(
                        onQuizItemChanged = { questionItemType, value, index ->
                            viewModel.updateQuestionData(
                                quizQuestionIndex = it,
                                questionItemType = questionItemType,
                                value = value,
                                answerIndex = index
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
                                viewModel.generateFirebaseQuiz()
                            }
                        ) {
                            Text(text = "Upload ${quizUiState.questions.size} questions to quiz")
                        }
                    }
                }
            }
        }
    }
}

enum class QuestionItemType {
    QUESTION,
    ANSWER,
    IMAGE,
    TIME
}
