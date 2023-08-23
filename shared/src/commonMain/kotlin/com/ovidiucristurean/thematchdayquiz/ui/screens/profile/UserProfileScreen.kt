package com.ovidiucristurean.thematchdayquiz.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class UserProfileScreen : Screen {

    @Composable
    override fun Content() {
        val localNavigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier
                    .clickable {
                        localNavigator.push(UserProfileScreen())
                    },
                text = "User profile screen placeholder",
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
