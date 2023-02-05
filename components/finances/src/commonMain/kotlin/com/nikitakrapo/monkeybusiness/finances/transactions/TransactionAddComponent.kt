package com.nikitakrapo.monkeybusiness.finances.transactions

import kotlinx.coroutines.flow.StateFlow

interface TransactionAddComponent {

    val state: StateFlow<State>

    data class State(
        val nameText: String,
        val isLoading: Boolean,
        val error: String,
    )
}