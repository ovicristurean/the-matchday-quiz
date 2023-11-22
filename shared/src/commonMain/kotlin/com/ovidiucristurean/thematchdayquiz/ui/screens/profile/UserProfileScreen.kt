package com.ovidiucristurean.thematchdayquiz.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import com.ovidiucristurean.widgets.button.MatchdayButton
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserProfileScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel: UserProfileViewModel by inject()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center,
        ) {

            MatchdayButton(
                onClick = {
                    viewModel.logout()
                }
            ) {
                Text(
                    text = "Log out",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
