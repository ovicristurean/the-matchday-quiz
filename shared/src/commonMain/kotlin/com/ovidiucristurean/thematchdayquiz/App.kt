package com.ovidiucristurean.thematchdayquiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.ovidiucristurean.splash.ui.SplashScreen
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.ui.navigation.MainScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.EmailSignInScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.RegisterScreen
import com.ovidiucristurean.thematchdayquiz.ui.screens.signup.SignInScreen

@Composable
fun App(
    darkTheme: Boolean,
) {
    val appNavigationState: AppNavigationState = rememberAppNavigationState()

    MatchdayQuizTheme(
        darkTheme = darkTheme
    ) {
        Navigator(screen = SplashScreen()) { navigator ->
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
