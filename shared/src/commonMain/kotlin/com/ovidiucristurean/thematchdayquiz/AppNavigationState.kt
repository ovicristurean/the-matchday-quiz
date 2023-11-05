package com.ovidiucristurean.thematchdayquiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
//import com.barabasizsolt.mova.auth.api.AuthenticationState
//import com.barabasizsolt.mova.domain.usecase.auth.IsLoggedInUseCase
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.AuthenticationState
import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.usecase.GetAuthenticationStateUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@Composable
internal fun rememberAppNavigationState(
    scope: CoroutineScope = rememberCoroutineScope(),
): AppNavigationState = remember { AppNavigationState(scope = scope) }


internal class AppNavigationState(scope: CoroutineScope) : KoinComponent {

    private val getAuthenticationStateUseCase by inject<GetAuthenticationStateUseCase>()
    var authState by mutableStateOf<AuthenticationState?>(value = null)
        private set

    init {
        scope.launch {
            getAuthenticationStateUseCase().collect {
                authState = it
            }
        }
    }
}
