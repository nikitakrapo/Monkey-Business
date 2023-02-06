package com.nikitakrapo.monkeybusiness.finances.transactions

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.StateFlow

interface TransactionAddComponent {

    val state: StateFlow<State>

    fun onNameTextChanged(text: String)
    fun onTransactionTypeSelected(type: TransactionType)
    fun onAmountTextChanged(text: String)
    fun onCurrencySelected(currency: Currency)

    fun onBackClicked()

    fun onAddClicked()

    data class State(
        val nameText: String,
        val nameError: String?,
        val selectedTransactionType: TransactionType,
        val amountText: String,
        val amountError: String?,
        val selectedCurrency: Currency,
        val isLoading: Boolean,
        val error: String?,
    )

    enum class TransactionType {
        Credit,
        Debit;

        companion object {
            val Default get() = Credit
        }
    }
}