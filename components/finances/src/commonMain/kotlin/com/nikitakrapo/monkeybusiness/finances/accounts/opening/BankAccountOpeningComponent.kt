package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.StateFlow

interface BankAccountOpeningComponent {

    val state: StateFlow<State>

    fun onCurrencySelected(index: Int)
    fun onBackClicked()
    fun onProceedClicked()

    data class State(
        val currencyList: List<Currency>,
        val selectedCurrency: Currency?,
        val query: String,
        val isLoading: Boolean,
        val isSearchOpened: Boolean,
    ) {
        val isProceedButtonVisible get() = selectedCurrency != null
    }
}