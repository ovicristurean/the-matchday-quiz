package com.ovidiucristurean.thematchdayquiz.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ovidiucristurean.thematchdayquiz.MatchdayDatabase

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MatchdayDatabase.Schema, context, "MatchdayDatabase.db")
    }
}