package com.nikitakrapo.account.exceptions

class AuthException(
    val authError: AuthError,
    val originalException: Exception,
) : Exception()
