package com.nikitakrapo.account

import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toDomainModel(): Account? {
    if (isAnonymous) {
        return Account.Anonymous(uid)
    }
    return email?.let {
        Account.Emailish(
            uid = uid,
            email = it,
        )
    }
}
