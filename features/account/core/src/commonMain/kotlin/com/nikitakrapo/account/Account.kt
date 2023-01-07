package com.nikitakrapo.account

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
sealed class Account : Parcelable {
    @Parcelize
    data class Emailish(
        val uid: String,
        val email: String,
    ) : Account(), Parcelable
}
