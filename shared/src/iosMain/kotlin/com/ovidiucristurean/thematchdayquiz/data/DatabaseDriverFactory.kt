package com.ovidiucristurean.thematchdayquiz.data

import com.ovidiucristurean.thematchdayquiz.database.MatchdayDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {

    /*actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            database.Schema,
            context,
            "matchday.db"
        )
    }*/
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = MatchdayDatabase.Schema,
            name = "matchday.db"
        )
    }


}
