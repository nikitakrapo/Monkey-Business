package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent.State
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.flow.StateFlow

class BankAccountsComponentImpl(
    componentContext: ComponentContext,
    featureFactory: FeatureFactory = FeatureFactory(),
    dependencies: BankAccountsDependencies,
) : BankAccountsComponent, ComponentContext by componentContext {

    private val bankAccountsRepository = dependencies.bankAccountsRepository
    private val productOpeningLandingRouter = dependencies.productOpeningLandingRouter

    private val feature = featureFactory.create<Intent, Intent, Effect, State, Nothing>(
        name = "BankAccounts",
        initialState = TODO(),
        intentToAction = TODO(),
        bootstrapper = TODO(),
        reducer = TODO(),
        actor = TODO(),
    )

    override val state: StateFlow<State> = feature.state

    override fun onAccountClicked(index: Int) {

    }

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }

    private sealed class Action {
        class AccountListUpdated(val accountList: List<BankAccount>) : Action()
    }

    private sealed class Intent {
        private class UpdateAccountList(val accountList: List<BankAccount>)
    }

    private sealed class Effect {

    }
}