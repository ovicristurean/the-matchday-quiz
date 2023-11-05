package com.ovidiucristurean.thematchdayquiz.ui.screens.auth

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

actual class AuthScreen : Screen, KoinComponent {

    private val authScreenState: AuthScreenState by inject(AuthScreenState::class.java)

    @Composable
    override fun Content() {
        val loginWithGoogleAccountLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            val data = result.data
            if (result.resultCode == Activity.RESULT_OK && data != null) {
                authScreenState.authenticateWithGoogle(intent = data)
            }
        }
        SideEffect {
            loginWithGoogleAccountLauncher.launch(authScreenState.intentForGoogleLogin)
        }
    }

}