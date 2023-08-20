package com.ovidiucristurean.thematchdayquiz

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun App(
    darkTheme: Boolean,
) {
    MatchdayQuizTheme(
        darkTheme = darkTheme
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text(
                modifier = Modifier,
                text = "Home screen",
            )
        }
    }
}

