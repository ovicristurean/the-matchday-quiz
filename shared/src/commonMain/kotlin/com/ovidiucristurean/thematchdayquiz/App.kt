package com.ovidiucristurean.thematchdayquiz

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.ovidiucristurean.thematchdayquiz.ui.navigation.MainScreen

@Composable
fun App(
    darkTheme: Boolean,
) {
    MatchdayQuizTheme(
        darkTheme = darkTheme
    ) {
        Navigator(MainScreen())
    }
}
