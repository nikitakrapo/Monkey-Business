package com.nikitakrapo.account

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

actual fun AccountManager(): AccountManager = FirebaseAccountManager(
    FirebaseAuthWrapper(Firebase.auth)
)