package com.nikitakrapo.navigation

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform