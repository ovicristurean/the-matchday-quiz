package com.ovidiucristurean.thematchdayquiz

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ovidiucristurean.thematchdayquiz.ui.theme.DarkColorScheme
import com.ovidiucristurean.thematchdayquiz.ui.theme.LightColorScheme
import com.ovidiucristurean.thematchdayquiz.ui.theme.Typography

@Composable
actual fun MatchdayQuizTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
