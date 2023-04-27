package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.coroutines.mapState
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finances.accounts.viewmodels.BankAccountViewState
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent.State as ViewState

class BankAccountsComponentImpl(
    componentContext: ComponentContext,
    featureFactory: FeatureFactory = FeatureFactory(),
    dependencies: BankAccountsDependencies,
) : BankAccountsComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    private val bankAccountsRepository = dependencies.bankAccountsRepository
    private val productOpeningLandingRouter = dependencies.productOpeningLandingRouter

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Nothing>(
        name = "BankAccounts",
        initialState = State(
            accountList = null,
            isLoading = true,
        ),
        intentToAction = {},
        bootstrapper = {

        },
        reducer = {},
        actor = {},
    )

    override val state: StateFlow<ViewState> = feature.state
        .mapState(scope, State::toViewState)

    override fun onAccountClicked(index: Int) {

    }

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }

    private data class State(
        val accountList: List<BankAccount>?,
        val isLoading: Boolean,
    ) {
        fun toViewState() = ViewState(
            accountList = accountList.map {},
            showShimmer = isLoading && accountList.isNullOrEmpty()
        )
    }

    private sealed class Action {
        class AccountListUpdated(val accountList: List<BankAccountViewState>?) : Action()
    }

    private sealed class Intent {
        private class UpdateAccountList(val accountList: List<BankAccountViewState>?)
    }

    private sealed class Effect {

    }
}