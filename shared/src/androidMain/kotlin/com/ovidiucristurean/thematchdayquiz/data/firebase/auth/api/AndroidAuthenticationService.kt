package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api

import android.content.Intent
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthResult
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import kotlinx.coroutines.flow.Flow

interface AndroidAuthenticationService : AuthenticationService {

    fun loginWithGoogleAccount(intent: Intent): Flow<AuthResult>

    fun getIntentForGoogleAccountLogin(): Intent

    fun loginWithFacebookAccount(): Flow<AuthResult>

    fun registerFacebookCallbackManager(requestCode: Int, resultCode: Int, data: Intent?)

}
