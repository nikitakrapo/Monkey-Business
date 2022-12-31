package com.nikitakrapo.account

sealed class Account(
    val uid: String,
) {
    class Emailish(
        uid: String,
        val email: String,
    ) : Account(uid)

    class Anonymous(
        uid: String,
    ) : Account(uid)
}