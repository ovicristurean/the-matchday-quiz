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
import com.ovidiucristurean.thematchdayquiz.data.UserRepository
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.AvailableQuizView
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.PastQuizzesView
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.FirebaseApp
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory

class HomeScreen(
    val onQuizOpenRequested: () -> Unit,
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = getViewModel(
            key = "home-screen",
            factory = viewModelFactory {
                HomeScreenViewModel()
            }
        )
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
                    onQuizOpenRequested()
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
                modifier = Modifier.fillMaxSize().padding(vertical = 16.dp)
            )
        }
    }
}