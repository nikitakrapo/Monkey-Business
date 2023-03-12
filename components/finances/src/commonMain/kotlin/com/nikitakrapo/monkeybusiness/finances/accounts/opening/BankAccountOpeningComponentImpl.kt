package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BankAccountOpeningComponentImpl(
    componentContext: ComponentContext,
    private val closeComponent: () -> Unit,
) : BankAccountOpeningComponent, ComponentContext by componentContext {

    override val state: StateFlow<BankAccountOpeningComponent.State> =
        MutableStateFlow(
            BankAccountOpeningComponent.State(
                isSearchOpened = false,
                query = "",
                currencyList = Currency.values().map {
                    CurrencyViewState(
                        isSelected = false,
                        fullName = "${it.symbol} currency",
                        code = it.code,
                    )
                },
            )
        )

    override fun onCurrencySelected(index: Int) {
        TODO("Not yet implemented")
    }

    override fun onSearchQueryUpdated(query: String) {
        TODO("Not yet implemented")
    }

    override fun onSearchClicked() {
        TODO("Not yet implemented")
    }

    override fun onBackClicked() {
        closeComponent()
    }

    override fun onProceedClicked() {
        TODO("Not yet implemented")
    }
}