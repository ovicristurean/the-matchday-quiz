package com.ovidiucristurean.thematchdayquiz.data.firebase.auth.implementation

import android.content.Intent
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationResult
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api.AndroidAuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.authenticate
import com.ovidiucristurean.thematchdayquiz.data.firebase.implementation.AuthenticationServiceImpl
import com.ovidiucristurean.thematchdayquiz.service.activityprovider_api.ActivityProvider
import dev.gitlive.firebase.auth.FacebookAuthProvider
import dev.gitlive.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.transform
import org.koin.core.component.inject

class AndroidAuthenticationServiceImpl : AuthenticationServiceImpl(), AndroidAuthenticationService {

    private val activityProvider: ActivityProvider by inject()
    private val facebookAuthManager: CallbackManager = CallbackManager.Factory.create()

    private val googleRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(WEB_CLIENT_ID)
        .requestEmail()
        .build()

    private val googleClient = GoogleSignIn.getClient(activityProvider.get(), googleRequest)

    override fun loginWithFacebookAccount(): Flow<AuthenticationResult> = callbackFlow {
        LoginManager.getInstance()
            .logInWithReadPermissions(activityProvider.get(), listOf("public_profile", "email"))
        LoginManager.getInstance().registerCallback(
            facebookAuthManager,
            object : FacebookCallback<LoginResult> {
                override fun onError(error: FacebookException) {
                    trySendBlocking(element = AuthenticationResult.Failure(error = error.message.orEmpty()))
                }

                override fun onSuccess(result: LoginResult) {
                    trySendBlocking(element = AuthenticationResult.Success(data = result.accessToken.token))
                }

                override fun onCancel() {
                    trySendBlocking(element = AuthenticationResult.Dismissed())
                }
            }
        )
        awaitClose { LoginManager.getInstance().unregisterCallback(facebookAuthManager) }
    }.transform { result ->
        when (result) {
            is AuthenticationResult.Success -> {
                val authCredential =
                    FacebookAuthProvider.credential(accessToken = result.data as String)
                authenticate(
                    authFunction = { getFirebaseAuth().signInWithCredential(authCredential = authCredential) },
                    sideEffect = { setAuthenticationState(state = AuthenticationState.Logged) }
                ).collect { res -> emit(value = res) }
            }

            is AuthenticationResult.Failure -> emit(value = AuthenticationResult.Failure(error = result.error))
            is AuthenticationResult.Dismissed -> emit(value = AuthenticationResult.Dismissed())
        }
    }

    override fun registerFacebookCallbackManager(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookAuthManager.onActivityResult(requestCode, resultCode, data)
    }

    override fun getIntentForGoogleAccountLogin(): Intent = googleClient.signInIntent

    override fun loginWithGoogleAccount(intent: Intent): Flow<AuthenticationResult> = callbackFlow {
        GoogleSignIn.getSignedInAccountFromIntent(intent)
            .addOnSuccessListener { result -> trySend(element = AuthenticationResult.Success(data = result.idToken)) }
            .addOnFailureListener { error -> trySend(element = AuthenticationResult.Failure(error = error.message.orEmpty())).isFailure }
        awaitClose { }
    }.transform { result ->
        when (result) {
            is AuthenticationResult.Success -> {
                val authCredential = GoogleAuthProvider.credential(
                    idToken = result.data as String,
                    accessToken = null
                )
                authenticate(
                    authFunction = { getFirebaseAuth().signInWithCredential(authCredential = authCredential) },
                    sideEffect = { setAuthenticationState(state = AuthenticationState.Logged) }
                ).collect { res -> emit(value = res) }
            }

            is AuthenticationResult.Failure -> emit(value = AuthenticationResult.Failure(error = result.error))
            is AuthenticationResult.Dismissed -> emit(value = AuthenticationResult.Dismissed())
        }
    }

    override suspend fun logOut() {
        super.logOut()
        googleClient.signOut()
    }
}