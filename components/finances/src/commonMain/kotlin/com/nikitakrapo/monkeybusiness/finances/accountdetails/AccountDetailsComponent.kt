package com.nikitakrapo.monkeybusiness.finances.accountdetails

import kotlinx.coroutines.flow.StateFlow

interface AccountDetailsComponent {

    val state: StateFlow<State>

    data class State(
        val name: String,
    )
}