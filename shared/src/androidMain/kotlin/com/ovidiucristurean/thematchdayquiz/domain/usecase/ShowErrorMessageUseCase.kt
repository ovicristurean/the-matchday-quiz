package com.ovidiucristurean.thematchdayquiz.domain.usecase

import android.content.Context
import android.widget.Toast

actual class ShowErrorMessageUseCase(
    private val appContext: Context
) {
    actual fun showErrorMessage(message: String) {
        Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
    }

}