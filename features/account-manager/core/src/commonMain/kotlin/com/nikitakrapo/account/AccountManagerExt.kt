package com.nikitakrapo.account

val AccountManager.currentAccount get() = account.value

val AccountManager.hasAuthorizedUser get() = currentAccount != null
