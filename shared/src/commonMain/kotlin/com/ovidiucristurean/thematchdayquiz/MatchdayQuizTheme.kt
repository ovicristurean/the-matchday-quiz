package com.ovidiucristurean.thematchdayquiz

import androidx.compose.runtime.Composable

@Composable
expect fun MatchdayQuizTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
)
