package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api

import android.content.Intent
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationResult
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import kotlinx.coroutines.flow.Flow

interface AndroidAuthenticationService : AuthenticationService {

    fun loginWithGoogleAccount(intent: Intent): Flow<AuthenticationResult>

    fun getIntentForGoogleAccountLogin(): Intent

    fun loginWithFacebookAccount(): Flow<AuthenticationResult>

    fun registerFacebookCallbackManager(requestCode: Int, resultCode: Int, data: Intent?)

}
