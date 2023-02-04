package com.nikitakrapo.monkeybusiness.finances

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finance.models.MoneyAmount
import com.nikitakrapo.monkeybusiness.finance.models.Transaction
import com.nikitakrapo.monkeybusiness.finances.FinancesComponent.State
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class FinancesComponentImpl(
    componentContext: ComponentContext,
    private val dependencies: FinancesDependencies,
    featureFactory: FeatureFactory = FeatureFactory(),
) : FinancesComponent, ComponentContext by componentContext {

    private val transactionAddRouter get() = dependencies.transactionAddRouter
    private val transactionsRepository get() = dependencies.transactionsRepository

    override val state: StateFlow<State> get() = feature.state

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Nothing>(
        name = "FinancesFeature",
        initialState = State(
            moneyAmount = MoneyAmount(
                amount = 123456,
                currency = Currency.GBP,
            ),
            transactionsList = null,
            transactionsLoading = true,
        ),
        intentToAction = { it },
        reducer = { effect ->
            when (effect) {
                is Effect.TransactionsLoaded -> copy(
                    transactionsList = effect.transactionList,
                    transactionsLoading = false,
                )
            }
        },
        actor = { action, _ ->
            when (action) {
                Intent.ObserveTransactions -> flow {
                    transactionsRepository.getAllTransactions()
                        .map(Effect::TransactionsLoaded)
                        .collect(::emit)
                }
            }
        },
        bootstrapper = {
            flowOf(Intent.ObserveTransactions)
        },
    )

    override fun onAddTransactionClicked() {
        transactionAddRouter.openTransactionAdd()
    }

    private sealed class Intent {
        object ObserveTransactions : Intent()
    }

    private sealed class Effect {
        class TransactionsLoaded(val transactionList: List<Transaction>) : Effect()
    }
}
