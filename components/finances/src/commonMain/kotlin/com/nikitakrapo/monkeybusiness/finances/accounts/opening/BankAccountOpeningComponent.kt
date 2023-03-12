package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import kotlinx.coroutines.flow.StateFlow

interface BankAccountOpeningComponent {

    val state: StateFlow<State>

    fun onCurrencySelected(index: Int)
    fun onSearchQueryUpdated(query: String)
    fun onSearchClicked()
    fun onBackClicked()
    fun onProceedClicked()

    data class State(
        val isSearchOpened: Boolean,
        val isProceedButtonVisible: Boolean,
        val query: String,
        val currencyList: List<CurrencyViewState>,
        val selectedCurrencyIndex: Int?,
    )
}