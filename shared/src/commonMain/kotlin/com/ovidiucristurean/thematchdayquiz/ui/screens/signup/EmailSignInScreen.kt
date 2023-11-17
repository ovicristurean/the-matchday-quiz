package com.ovidiucristurean.thematchdayquiz.ui.screens.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.thematchdayquiz.ui.navigation.MainScreen
import com.ovidiucristurean.widgets.button.MatchdayButton
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class EmailSignInScreen : Screen, KoinComponent {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: SignInViewModel by inject()
        val navigator = LocalNavigator.currentOrThrow

        LaunchedEffect(viewModel.authState) {
            viewModel.authState.collect { authenticationState ->
                when (authenticationState) {
                    LoginResult.LOGGED -> {
                        navigator.push(MainScreen())
                    }

                    LoginResult.IDLE -> {
                        //do nothing for now
                    }

                    LoginResult.NOT_LOGGED -> {
                        //TODO handle user not logged in properly
                        navigator.push(MainScreen())
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            var usernameText by remember { mutableStateOf(TextFieldValue("")) }
            var passwordText by remember { mutableStateOf(TextFieldValue("")) }

            Spacer(modifier = Modifier.weight(1f))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = usernameText,
                onValueChange = { newText ->
                    usernameText = newText
                },
                label = { Text(text = "Email") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordText,
                onValueChange = { newText ->
                    passwordText = newText
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.weight(1f))

            MatchdayButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = {
                    viewModel.login(usernameText.text, passwordText.text)
                }
            ) {
                Text(text = "Sign in")
            }
        }
    }
}

