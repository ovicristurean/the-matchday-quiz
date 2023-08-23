package com.ovidiucristurean.thematchdayquiz.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.ovidiucristurean.thematchdayquiz.ui.screens.homescreen.view.HomeScreenBottomBar
import com.ovidiucristurean.thematchdayquiz.ui.screens.homescreen.view.HomeScreenTopBar
import com.ovidiucristurean.thematchdayquiz.ui.screens.profile.UserProfileScreen

class MainScreen : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        TabNavigator(HomeTab) {
            Scaffold(
                topBar = {
                    HomeScreenTopBar(
                        onUserProfileClicked = {
                            navigator.push(UserProfileScreen())
                        }
                    )
                },
                bottomBar = {
                    HomeScreenBottomBar()
                }
            ) {
                CurrentTab()
            }
        }
    }
}