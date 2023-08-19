package com.ovidiucristurean.thematchdayquiz

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform