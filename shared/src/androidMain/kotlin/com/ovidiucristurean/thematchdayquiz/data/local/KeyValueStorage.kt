package com.ovidiucristurean.thematchdayquiz.data.local

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

//val delegate:SharedPreferences =

actual class KeyValueStorage(
    private val context: Context
) {
    private val delegate =
        context.getSharedPreferences("MatchdayPrefs", Context.MODE_PRIVATE)

    actual val settings: Settings =
        SharedPreferencesSettings(delegate)
}
