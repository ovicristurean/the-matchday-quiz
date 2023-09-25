package com.ovidiucristurean.quiz.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.ovidiucristurean.quiz.domain.model.QuizAnswer
import com.ovidiucristurean.quiz.domain.repository.QuizRepositoryImpl
import com.ovidiucristurean.quiz.presentation.widget.AnswerOptions
import com.ovidiucristurean.quiz.presentation.widget.QuizProgressView
import com.ovidiucristurean.widgets.button.MatchdayButton
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

        when (state) {
            QuizScreenUiState.QuizNotStarted -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Preparing the quiz for you"
                    )
                }
            }

            is QuizScreenUiState.QuizScreenInProgress -> {
                println("state is ${(state as QuizScreenUiState.QuizScreenInProgress).selectedAnswer}")
                QuizInProgressScreen(
                    state = state as QuizScreenUiState.QuizScreenInProgress,
                    onOptionSelected = { answer ->
                        viewModel.selectAnswer(answer)
                    }
                )
            }

            QuizScreenUiState.QuizFinished -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.tertiary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "You finished the quiz",
                        color = MaterialTheme.colorScheme.onTertiary
                    )

                    MatchdayButton(
                        onClick = {
                            navigator.pop()
                        }
                    ) {
                        Text(
                            text = "Close quiz",
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizInProgressScreen(
    state: QuizScreenUiState.QuizScreenInProgress,
    onOptionSelected: (QuizAnswer) -> Unit,
) {
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
                answers = state.currentQuestion?.answers ?: listOf(),
                onOptionSelected = onOptionSelected,
                selectedOption = state.selectedAnswer,
                modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 20.dp)
            )

            Text(
                modifier = Modifier.padding(vertical = 16.dp),
                text = "Total score: ${state.totalScore}",
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}
