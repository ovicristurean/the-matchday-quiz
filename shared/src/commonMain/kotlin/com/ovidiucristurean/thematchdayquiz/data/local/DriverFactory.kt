package com.ovidiucristurean.thematchdayquiz.data.local

import app.cash.sqldelight.db.SqlDriver
import com.ovidiucristurean.thematchdayquiz.MatchdayDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): MatchdayDatabase {
    val driver = driverFactory.createDriver()
    return MatchdayDatabase(driver)
}
