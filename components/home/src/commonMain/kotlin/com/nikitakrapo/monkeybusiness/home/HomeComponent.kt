package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import kotlinx.coroutines.flow.StateFlow

interface HomeComponent {

    val state: StateFlow<State>

    fun onSearchBarClicked()
    fun onAvatarClicked()
    fun onTopupClicked()
    fun onWithdrawClicked()

    data class State(
        val balance: MoneyAmount
    )
}
