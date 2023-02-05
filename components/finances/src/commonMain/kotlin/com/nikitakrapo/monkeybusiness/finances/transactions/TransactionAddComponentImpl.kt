package com.nikitakrapo.monkeybusiness.finances.transactions

import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponent.State
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddComponent.TransactionType
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

class TransactionAddComponentImpl(
    private val closeTransactionAdd: () -> Unit,
    featureFactory: FeatureFactory = FeatureFactory(),
) : TransactionAddComponent {

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Nothing>(
        name = "TransactionAddFeature",
        initialState = State(
            nameText = "",
            selectedTransactionType = TransactionType.Default,
            moneyAmountText = "",
            selectedCurrency = Currency.Default,
            isLoading = false,
            error = null,
        ),
        intentToAction = { it },
        reducer = { effect ->
            when (effect) {
                is Effect.CurrencySelected -> copy(
                    selectedCurrency = effect.currency,
                    error = null
                )
                is Effect.MoneyAmountTextChanged -> copy(
                    moneyAmountText = effect.text,
                    error = null
                )
                is Effect.NameTextChanged -> copy(
                    nameText = effect.text,
                    error = null
                )
                is Effect.TransactionTypeSelected -> copy(
                    selectedTransactionType = effect.type,
                    error = null
                )
            }
        },
        actor = { action, state ->
            when (action) {
                is Intent.ChangeMoneyAmountText -> flowOf(Effect.MoneyAmountTextChanged(action.text))
                is Intent.ChangeNameText -> flowOf(Effect.NameTextChanged(action.text))
                is Intent.SelectCurrency -> flowOf(Effect.CurrencySelected(action.currency))
                is Intent.SelectTransactionType -> flowOf(Effect.TransactionTypeSelected(action.type))
                Intent.AddTransaction -> TODO()
            }
        }
    )

    override val state: StateFlow<State> get() = feature.state

    override fun onNameTextChanged(text: String) {
        feature.accept(Intent.ChangeNameText(text))
    }

    override fun onTransactionTypeSelected(type: TransactionType) {
        feature.accept(Intent.SelectTransactionType(type))
    }

    override fun onMoneyAmountTextChanged(text: String) {
        feature.accept(Intent.ChangeMoneyAmountText(text))
    }

    override fun onCurrencySelected(currency: Currency) {
        feature.accept(Intent.SelectCurrency(currency))
    }

    override fun onBackClicked() {
        closeTransactionAdd()
    }

    override fun onAddClicked() {
        feature.accept(Intent.AddTransaction)
    }

    private sealed class Intent {
        class ChangeNameText(val text: String) : Intent()
        class SelectTransactionType(val type: TransactionType) : Intent()
        class ChangeMoneyAmountText(val text: String) : Intent()
        class SelectCurrency(val currency: Currency) : Intent()
        object AddTransaction : Intent()
    }

    private sealed class Effect {
        class NameTextChanged(val text: String) : Effect()
        class TransactionTypeSelected(val type: TransactionType) : Effect()
        class MoneyAmountTextChanged(val text: String) : Effect()
        class CurrencySelected(val currency: Currency) : Effect()
    }
}