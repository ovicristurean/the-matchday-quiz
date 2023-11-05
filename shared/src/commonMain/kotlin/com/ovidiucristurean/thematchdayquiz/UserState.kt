package com.ovidiucristurean.thematchdayquiz

sealed class UserState {

    data class NotSignedIn(
        val onSignInClicked: () -> Unit,
    ) : UserState()

    object SignedIn : UserState()
}