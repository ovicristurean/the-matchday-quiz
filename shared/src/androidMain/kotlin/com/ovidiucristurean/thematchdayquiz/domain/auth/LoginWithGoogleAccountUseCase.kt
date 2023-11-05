package com.ovidiucristurean.thematchdayquiz.domain.auth

import android.content.Intent
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api.AndroidAuthenticationService
import kotlinx.coroutines.flow.filterNotNull

class LoginWithGoogleAccountUseCase(
    private val androidAuthenticationService: AndroidAuthenticationService
) {

    operator fun invoke(intent: Intent) =
        androidAuthenticationService.loginWithGoogleAccount(intent).filterNotNull()

}
