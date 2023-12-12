package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class SignInScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            EmailLoginButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                navigator.push(EmailSignInScreen())
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            RegisterButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                navigator.push(RegisterScreen())
            }
        }
    }

    @Composable
    fun EmailLoginButton(
        modifier: Modifier = Modifier,
        onLoginClicked: () -> Unit
    ) {
        Button(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            onClick = onLoginClicked
        ) {
            Text(
                text = "Sign in with e-mail",
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun RegisterButton(
        modifier: Modifier = Modifier,
        onRegisterClicked: () -> Unit
    ) {
        Button(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            onClick = onRegisterClicked
        ) {
            Text(
                text = "Register",
                textAlign = TextAlign.Center
            )
        }
    }
}
