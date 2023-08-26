package com.ovidiucristurean.thematchdayquiz.ui.screens.quiz

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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
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
                QuizViewModel()
            }
        )
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)
            ) {
                QuizProgressView(
                    currentQuestionNumber = state.currentQuestionNumber ?: 0,
                    totalQuestions = state.numberOfQuestions ?: 0,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Who scored an own goal for Nottingham this gameweek?",
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )

                Image(
                    modifier = Modifier.size(200.dp),
                    painter = rememberImagePainter(state.imageUrl ?: ""),
                    contentDescription = null,
                )

                AnswerOptions(
                    answerOne = state.answerOne ?: "",
                    answerTwo = state.answerTwo ?: "",
                    answerThree = state.answerThree ?: "",
                    answerFour = state.answerFour ?: "",
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
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
        modifier: Modifier,
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MatchdayButton(
                    modifier = Modifier.weight(1f).height(40.dp),
                ) {
                    Text(
                        text = answerOne,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                MatchdayButton(
                    modifier = Modifier.weight(1f).height(40.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        text = answerTwo,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                MatchdayButton(
                    modifier = Modifier.weight(1f).height(40.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        text = answerThree,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                MatchdayButton(
                    modifier = Modifier.weight(1f).height(40.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        text = answerFour,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
