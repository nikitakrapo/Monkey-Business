package com.nikitakrapo.account.firebase

import com.google.firebase.auth.FirebaseUser
import com.nikitakrapo.account.models.Account

fun FirebaseUser.toDomainModel(): Account? {
    return email?.let {
        Account.Emailish(
            uid = uid,
            email = it,
        )
    }
}
