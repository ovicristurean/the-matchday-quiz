package com.ovidiucristurean.thematchdayquiz.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.ovidiucristurean.thematchdayquiz.MatchdayDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MatchdayDatabase.Schema, "MatchdayDatabase.db")
    }
}
