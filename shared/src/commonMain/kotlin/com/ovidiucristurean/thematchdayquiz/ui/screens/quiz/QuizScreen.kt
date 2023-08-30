package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.thematchdayquiz.domain.repository.QuizRepositoryImpl
import com.ovidiucristurean.thematchdayquiz.ui.widget.button.MatchdayButton
import com.seiko.imageloader.rememberImagePainter
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

class QuizScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "quiz-screen",
            factory = viewModelFactory {
                QuizViewModel(QuizRepositoryImpl())
            }
        )
        val state by viewModel.state.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        when (state.quizState) {
            QuizState.NOT_STARTED -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Preparing the quiz for you"
                    )
                }
            }

            QuizState.IN_PROGRESS -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    QuizProgressView(
                        currentQuestionNumber = state.currentQuestionNumber ?: 0,
                        totalQuestions = state.numberOfQuestions ?: 0,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    AnimatedVisibility(
                        visible = state.timeLeftForQuestion != null
                    ) {
                        Text(
                            text = "Remaining time:\n${state.timeLeftForQuestion ?: ""}",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = state.currentQuestion?.question ?: "",
                            color = MaterialTheme.colorScheme.onPrimary,
                            textAlign = TextAlign.Center
                        )

                        Image(
                            modifier = Modifier.size(200.dp),
                            painter = rememberImagePainter(state.currentQuestion?.imageUrl ?: ""),
                            contentDescription = null,
                        )

                        AnswerOptions(
                            answerOne = state.currentQuestion?.answerOne ?: "",
                            answerTwo = state.currentQuestion?.answerTwo ?: "",
                            answerThree = state.currentQuestion?.answerThree ?: "",
                            answerFour = state.currentQuestion?.answerFour ?: "",
                            onOptionSelected = {
                                viewModel.selectAnswer(it)
                            },
                            selectedOption = state.answerOptionsState,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                        )
                    }
                }
            }

            QuizState.FINISHED -> {
                navigator.pop()
            }
        }
    }

    @Composable
    private fun QuizProgressView(
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

    @Composable
    private fun AnswerOptions(
        answerOne: String,
        answerTwo: String,
        answerThree: String,
        answerFour: String,
        selectedOption: AnswerOptionsState,
        onOptionSelected: (AnswerOptionsState) -> Unit,
        modifier: Modifier,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            MatchdayButton(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                onClick = {
                    onOptionSelected(AnswerOptionsState.OPTION_1_SELECTED)
                },
                isEnabled = selectedOption == AnswerOptionsState.OPTION_1_SELECTED ||
                        selectedOption == AnswerOptionsState.NOTHING_SELECTED
            ) {
                Text(
                    text = answerOne,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            MatchdayButton(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                onClick = {
                    onOptionSelected(AnswerOptionsState.OPTION_2_SELECTED)
                },
                isEnabled = selectedOption == AnswerOptionsState.OPTION_2_SELECTED ||
                        selectedOption == AnswerOptionsState.NOTHING_SELECTED
            ) {
                Text(
                    text = answerTwo,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            MatchdayButton(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                onClick = {
                    onOptionSelected(AnswerOptionsState.OPTION_3_SELECTED)
                },
                isEnabled = selectedOption == AnswerOptionsState.OPTION_3_SELECTED ||
                        selectedOption == AnswerOptionsState.NOTHING_SELECTED
            ) {
                Text(
                    text = answerThree,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            MatchdayButton(
                modifier = Modifier.fillMaxWidth().height(40.dp),
                onClick = {
                    onOptionSelected(AnswerOptionsState.OPTION_4_SELECTED)
                },
                isEnabled = selectedOption == AnswerOptionsState.OPTION_4_SELECTED ||
                        selectedOption == AnswerOptionsState.NOTHING_SELECTED
            ) {
                Text(
                    text = answerFour,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
