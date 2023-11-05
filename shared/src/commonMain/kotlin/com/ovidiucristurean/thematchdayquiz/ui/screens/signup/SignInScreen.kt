package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class SignInScreen(
    val onGoogleSignInClicked: () -> Unit
) : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            GoogleLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }

    @Composable
    fun GoogleLoginButton(
        modifier: Modifier = Modifier,
    ) {
        Button(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            onClick = onGoogleSignInClicked
        ) {
            Text(
                text = "Sign in with Google",
                textAlign = TextAlign.Center
            )
        }
    }

}
