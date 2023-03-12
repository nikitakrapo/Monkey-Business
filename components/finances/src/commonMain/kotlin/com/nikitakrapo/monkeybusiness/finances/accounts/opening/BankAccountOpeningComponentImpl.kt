package com.nikitakrapo.monkeybusiness.finances.accounts.opening

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.finance.models.Currency
import com.nikitakrapo.monkeybusiness.finances.accounts.opening.BankAccountOpeningComponent.State
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class BankAccountOpeningComponentImpl(
    componentContext: ComponentContext,
    featureFactory: FeatureFactory = FeatureFactory(),
    private val closeComponent: () -> Unit,
) : BankAccountOpeningComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    // consider moving it into the state
    private val allCurrencies = Currency.values().map {
        CurrencyViewState(
            fullName = "${it.symbol} currency",
            code = it.code,
        )
    }

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Event>(
        name = "Bank Account Opening",
        initialState = State(
            isSearchOpened = false,
            isProceedButtonVisible = false,
            query = "",
            currencyList = allCurrencies,
            selectedCurrencyIndex = null,
        ),
        intentToAction = { it },
        reducer = { effect ->
            when (effect) {
                is Effect.CurrencySelected -> {
                    val newSelectedIndex =
                        if (effect.index == selectedCurrencyIndex) null else effect.index
                    copy(
                        selectedCurrencyIndex = newSelectedIndex,
                        isProceedButtonVisible = newSelectedIndex != null,
                    )
                }
                Effect.SearchOpened -> copy(isSearchOpened = true)
                Effect.SearchClosed -> copy(isSearchOpened = false)
                is Effect.SearchQueryUpdated -> copy(query = effect.text)
                is Effect.CurrencyListUpdated -> copy(currencyList = effect.list)
                Effect.ScreenClosed -> copy()
            }
        },
        actor = { action, state ->
            when (action) {
                is Intent.SelectCurrency -> flowOf(Effect.CurrencySelected(action.index))
                is Intent.UpdateSearchQuery -> flowOf(
                    Effect.SearchQueryUpdated(action.text),
                    Effect.CurrencyListUpdated(
                        allCurrencies.filter {
                            it.fullName.contains(action.text, ignoreCase = true)
                                    || it.code.contains(action.text, ignoreCase = true)
                        }
                    )
                )

                Intent.OpenSearch -> flowOf(Effect.SearchOpened)
                Intent.GoBack -> {
                    if (state.isSearchOpened) {
                        flowOf(
                            Effect.SearchClosed,
                            Effect.SearchQueryUpdated(text = ""),
                            Effect.CurrencyListUpdated(list = allCurrencies)
                        )
                    } else {
                        flowOf(Effect.ScreenClosed)
                    }
                }

                Intent.Proceed -> TODO()
            }
        },
        eventsPublisher = { _, effect, _ ->
            if (effect is Effect.ScreenClosed) Event.CloseScreen else null
        }
    )

    init {
        scope.launch {
            feature.events.collect {
                when (it) {
                    Event.CloseScreen -> closeComponent()
                }
            }
        }
    }

    override val state: StateFlow<State> get() = feature.state

    override fun onCurrencySelected(index: Int) {
        feature.accept(Intent.SelectCurrency(index = index))
    }

    override fun onSearchQueryUpdated(query: String) {
        feature.accept(Intent.UpdateSearchQuery(text = query))
    }

    override fun onSearchClicked() {
        feature.accept(Intent.OpenSearch)
    }

    override fun onBackClicked() {
        feature.accept(Intent.GoBack)
    }

    override fun onProceedClicked() {
        feature.accept(Intent.Proceed)
    }

    private sealed class Intent {
        class SelectCurrency(val index: Int) : Intent()
        object Proceed : Intent()
        object OpenSearch : Intent()
        class UpdateSearchQuery(val text: String) : Intent()
        object GoBack : Intent()
    }

    private sealed class Effect {
        class CurrencySelected(val index: Int) : Effect()
        class SearchQueryUpdated(val text: String) : Effect()
        class CurrencyListUpdated(val list: List<CurrencyViewState>) : Effect()
        object SearchOpened : Effect()
        object SearchClosed : Effect()
        object ScreenClosed : Effect()
    }

    private sealed class Event {
        object CloseScreen : Event()
    }
}