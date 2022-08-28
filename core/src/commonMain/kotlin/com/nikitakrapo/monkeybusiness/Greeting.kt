package com.nikitakrapo.monkeybusiness

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}