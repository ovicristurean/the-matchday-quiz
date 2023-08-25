package com.ovidiucristurean.thematchdayquiz.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class HomeScreen(
    val onQuizOpenRequested: () -> Unit,
) : Screen {

    @Composable
    override fun Content() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary),
            //contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable {
                    onQuizOpenRequested()
                },
                text = "Home Screen",
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }

}