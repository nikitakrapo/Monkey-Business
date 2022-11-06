package com.nikitakrapo.backpress

interface BackHandler {

    fun register(callback: BackCallback)

    fun unregister(callback: BackCallback)
}