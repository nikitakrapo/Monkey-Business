package com.nikitakrapo.monkeybusiness.home

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}