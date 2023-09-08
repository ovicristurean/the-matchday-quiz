package com.ovidiucristurean.thematchdayquiz.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.HomeScreen

internal class HomeTab(
    val onQuizOpenRequested: () -> Unit,
) : Tab {

    @Composable
    override fun Content() {
        Navigator(
            HomeScreen(
                onQuizOpenRequested = onQuizOpenRequested,
            )
        )
    }

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 0u,
            title = "Home",
            icon = rememberVectorPainter(Icons.Default.Home),
        )
}
