package com.nikitakrapo.account

import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomainModel(): Account? {
    return email?.let {
        Account.Emailish(
            uid = uid,
            email = it,
        )
    }
}
