package com.ovidiucristurean.thematchdayquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.ovidiucristurean.thematchdayquiz.ui.navigation.HomeTab
import com.ovidiucristurean.thematchdayquiz.ui.screens.homescreen.view.HomeScreenBottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    darkTheme: Boolean,
) {
    MatchdayQuizTheme(
        darkTheme = darkTheme
    ) {
        TabNavigator(HomeTab()) {
            Scaffold(
                topBar = {
                    Box(
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "Ovidiu",
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                bottomBar = {
                    HomeScreenBottomBar()
                }
            ) {
                CurrentScreen()
            }
        }
    }
}

