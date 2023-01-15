package com.nikitakrapo.account

import com.nikitakrapo.account.models.Account

val AccountManager.currentAccount get() = account.value

val AccountManager.hasAuthorizedUser get() = currentAccount?.let {
    it is Account.Emailish
} ?: false