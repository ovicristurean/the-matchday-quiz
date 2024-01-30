package com.ovidiucristurean.thematchdayquiz.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.AvailableQuizView
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.PastQuizzesView
import com.ovidiucristurean.thematchdayquiz.ui.screens.quiz.QuizScreen
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: HomeScreenViewModel by inject()
        var spacerWidth by remember { mutableStateOf(0) }
        val state by viewModel.state.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
        ) {
            AvailableQuizView(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 2f)
                    .padding(all = 16.dp),
                currentQuizState = state.currentQuiz,
                onClick = {
                    navigator.parent?.parent?.push(QuizScreen())
                }
            )

            Spacer(
                modifier = Modifier.fillMaxWidth().height(2.dp).background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.outline,
                            Color.Transparent
                        ),
                        startX = spacerWidth / 3f,
                    ),
                ).onGloballyPositioned { layoutCoordinates ->
                    spacerWidth = layoutCoordinates.size.width
                }
            )

            PastQuizzesView(
                modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
                numberOfItems = state.pastQuizzes.size,
                onQuizClicked = {
                    //TODO implement screen to view past quiz results
                }
            )
        }
    }
}