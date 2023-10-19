package com.ovidiucristurean.thematchdayquiz.data

import android.content.Context
import com.ovidiucristurean.thematchdayquiz.database.MatchdayDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context,
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            MatchdayDatabase.Schema,
            context,
            "matchday.db"
        )
    }
}