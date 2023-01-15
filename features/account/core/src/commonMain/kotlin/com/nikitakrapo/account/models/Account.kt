package com.nikitakrapo.account.models

import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize

@Parcelize
data class Account(
    val uid: String,
    val email: String,
) : Parcelable
