package com.nikitakrapo.monkeyforecast

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}