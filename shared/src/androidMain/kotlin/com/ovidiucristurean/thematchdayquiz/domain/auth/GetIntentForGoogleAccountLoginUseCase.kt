package com.ovidiucristurean.thematchdayquiz.domain.auth

import com.ovidiucristurean.thematchdayquiz.data.firebase.auth.api.AndroidAuthenticationService

class GetIntentForGoogleAccountLoginUseCase(private val service: AndroidAuthenticationService) {

    operator fun invoke() = service.getIntentForGoogleAccountLogin()
}
