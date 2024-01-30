package com.ovidiucristurean.thematchdayquiz.data.local

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

actual class KeyValueStorage {

    private val delegate = NSUserDefaults()

    actual val settings: Settings
        get() = NSUserDefaultsSettings(delegate)
}
