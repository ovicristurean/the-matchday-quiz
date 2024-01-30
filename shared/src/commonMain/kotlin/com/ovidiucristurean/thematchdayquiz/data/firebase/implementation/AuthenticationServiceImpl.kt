package com.ovidiucristurean.thematchdayquiz.data.firebase.implementation

import com.ovidiucristurean.thematchdayquiz.data.USER_ID_KEY
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationResult
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationService
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.authenticate
import com.ovidiucristurean.thematchdayquiz.data.firebase.quiz.User
import com.ovidiucristurean.thematchdayquiz.data.local.KeyValueStorage
import com.russhwolf.settings.set
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class AuthenticationServiceImpl : AuthenticationService, KoinComponent {
    private val firebaseAuth: FirebaseAuth = Firebase.auth
    private val firebaseFirestore: FirebaseFirestore = Firebase.firestore

    private val _authenticationState =
        MutableStateFlow<AuthenticationState>(value = AuthenticationState.Idle)
    override val authenticationState: Flow<AuthenticationState> = _authenticationState

    private val keyValueStorage by inject<KeyValueStorage>()

    init {
        _authenticationState.value = if (firebaseAuth.currentUser == null)
            AuthenticationState.NotLogged
        else
            AuthenticationState.Logged
    }

    fun getFirebaseAuth(): FirebaseAuth = firebaseAuth

    fun setAuthenticationState(state: AuthenticationState) {
        _authenticationState.value = state
    }

    override fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<AuthenticationResult> =
        authenticate(
            authFunction = { firebaseAuth.signInWithEmailAndPassword(email, password) },
            sideEffect = {
                insertFirebaseUser(it.user)
                it.user?.uid?.let { userId ->
                    saveUserId(userId)
                }
                _authenticationState.value = AuthenticationState.Logged
            }
        )

    override fun registerWithEmailAndPassWord(
        email: String,
        password: String
    ): Flow<AuthenticationResult> =
        authenticate(
            authFunction = { firebaseAuth.createUserWithEmailAndPassword(email, password) },
            sideEffect = {
                insertFirebaseUser(it.user)
                it.user?.uid?.let { userId ->
                    saveUserId(userId)
                }
                _authenticationState.value = AuthenticationState.Logged
            }
        )

    override suspend fun logOut() {
        firebaseAuth.signOut()
        _authenticationState.value = AuthenticationState.NotLogged
    }

    override suspend fun resetPassword(email: String): Unit =
        firebaseAuth.sendPasswordResetEmail(email)

    private fun insertFirebaseUser(firebaseUser: FirebaseUser?) {
        val usersCollection = firebaseFirestore.collection("users")
        firebaseUser?.let { user ->
            CoroutineScope(Job() + Dispatchers.IO).launch {
                usersCollection.add(
                    User.serializer(),
                    User(
                        uid = user.uid,
                        email = user.email ?: ""
                    )
                )
            }
        }
    }

    private fun saveUserId(userId: String) {
        keyValueStorage.settings[USER_ID_KEY] = userId
    }

    companion object {
        const val WEB_CLIENT_ID: String =
            "693786424707-cecd7fpq40ppp33369sd26728po0u0ed.apps.googleusercontent.com"
    }
}
