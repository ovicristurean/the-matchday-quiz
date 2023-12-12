package com.ovidiucristurean.thematchdayquiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.ui.navigation.MainScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.EmailSignInScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.RegisterScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.SignInScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.splash.SplashScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun App(
    darkTheme: Boolean,
    resourcePath:String
) {
    val appNavigationState: AppNavigationState = rememberAppNavigationState()

    MatchdayQuizTheme(
        darkTheme = darkTheme
    ) {
        var splashVisible by rememberSaveable {
            mutableStateOf(true)
        }

        rememberCoroutineScope().launch {
            delay(6000L)
            splashVisible = false
        }

        if (splashVisible) {
            Navigator(screen = SplashScreen(resourcePath))
        } else {
            Navigator(screen = SplashScreen(resourcePath)) { navigator ->
                LaunchedEffect(appNavigationState.authState) {
                    navigator.popUntilRoot()
                    when (appNavigationState.authState) {
                        AuthenticationState.Logged -> {
                            navigator.replace(item = MainScreen())
                        }

                        AuthenticationState.NotLogged -> {
                            navigator.replace(item = SignInScreen(
                                onGoogleSignInClicked = {
                                    navigator.push(EmailSignInScreen())
                                },
                                onRegisterClicked = {
                                    navigator.push(RegisterScreen())
                                }
                            ))
                        }

                        else -> Unit
                    }
                }
                CurrentScreen()
            }
        }
    }
}
