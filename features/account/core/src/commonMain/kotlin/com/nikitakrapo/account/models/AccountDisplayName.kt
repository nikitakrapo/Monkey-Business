package com.nikitakrapo.account.models

fun Account.getDisplayName(): String {
    username?.let { return it }
    val atCharIndex = email.indexOf(char = '@')
    return if (atCharIndex != -1) email.substring(0 until atCharIndex) else email
}
