package com.ovidiucristurean.thematchdayquiz.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.ovidiucristurean.thematchdayquiz.ui.screens.developersettings.DeveloperSettingsScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.HomeScreenBottomBar
import com.ovidiucristurean.thematchdayquiz.ui.screens.home.view.HomeScreenTopBar
import com.ovidiucristurean.thematchdayquiz.ui.screens.profile.UserProfileScreen

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val homeTab = HomeTab()

        TabNavigator(homeTab) {
            Scaffold(
                topBar = {
                    HomeScreenTopBar(
                        onUserProfileClicked = {
                            navigator.push(UserProfileScreen())
                        },
                        onDeveloperSettingsClicked = {
                            navigator.push(DeveloperSettingsScreen())
                        }
                    )
                },
                bottomBar = {
                    HomeScreenBottomBar(homeTab)
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding(),
                        )
                ) {
                    CurrentTab()
                }
            }
        }
    }
}