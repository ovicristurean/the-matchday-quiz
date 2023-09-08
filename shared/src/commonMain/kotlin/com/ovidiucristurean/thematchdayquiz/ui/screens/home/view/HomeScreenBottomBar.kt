package com.ovidiucristurean.thematchdayquiz.ui.screens.home.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.ovidiucristurean.thematchdayquiz.ui.navigation.HomeTab
import com.ovidiucristurean.thematchdayquiz.ui.navigation.RankingsTab

@Composable
internal fun HomeScreenBottomBar(homeTab: HomeTab) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        BottomBarItem(
            tab = homeTab,
        )

        BottomBarItem(
            tab = RankingsTab,
        )
    }
}

@Composable
private fun RowScope.BottomBarItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = {
            tabNavigator.current = tab
        },
        label = {
            Text(
                text = tab.options.title,
            )
        },
        icon = {
            Icon(
                painter = tab.options.icon ?: rememberVectorPainter(Icons.Rounded.QuestionMark),
                contentDescription = null
            )
        }
    )
}
