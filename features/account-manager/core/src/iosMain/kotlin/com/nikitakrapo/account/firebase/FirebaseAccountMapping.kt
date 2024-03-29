package com.nikitakrapo.account.firebase

import cocoapods.FirebaseAuth.FIRUser
import com.nikitakrapo.account.models.Account

fun FIRUser.toDomainModel(): Account? {
    return email?.let {
        Account(
            uid = uid,
            email = it,
            username = displayName?.takeIf(String::isNotEmpty),
            photoUrl = photoURL?.absoluteString?.takeIf(String::isNotEmpty),
        )
    }
}
