package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.decompose.ComponentContext
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent.State
import com.nikitakrapo.mvi.feature.FeatureFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BankAccountsComponentImpl(
    componentContext: ComponentContext,
    featureFactory: FeatureFactory = FeatureFactory(),
    dependencies: BankAccountsDependencies,
) : BankAccountsComponent, ComponentContext by componentContext {

    private val scope = coroutineScope(Dispatchers.Main)

    private val bankAccountsRepository = dependencies.bankAccountsRepository
    private val productOpeningLandingRouter = dependencies.productOpeningLandingRouter

    private val feature = featureFactory.create<Intent, Action, Effect, State, Nothing>(
        name = "BankAccounts",
        initialState = State(
            accountList = null,
            isLoading = true,
        ),
        intentToAction = {
            when (it) {
                is Intent.UpdateAccountList -> Action.AccountListUpdated(
                    accountList = it.accountList
                )
            }
        },
        reducer = {
            when (it) {
                is Effect.AccountListUpdated -> copy(
                    accountList = it.accountList,
                    isLoading = false,
                )
            }
        },
        actor = { action, _ ->
            when (action) {
                is Action.AccountListUpdated -> flowOf(
                    Effect.AccountListUpdated(
                        accountList = action.accountList
                    )
                )
            }
        },
        bootstrapper = {
            scope.launch { bankAccountsRepository.updateBankAccounts() }
            bankAccountsRepository.getBankAccounts()
                .map(Action::AccountListUpdated)
        },
    )

    override val state: StateFlow<State> = feature.state

    override fun onAccountClicked(index: Int) {
        TODO()
    }

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }

    private sealed class Intent {
        class UpdateAccountList(val accountList: List<BankAccount>) : Intent()
    }

    private sealed class Action {
        class AccountListUpdated(val accountList: List<BankAccount>) : Action()
    }

    private sealed class Effect {
        class AccountListUpdated(val accountList: List<BankAccount>) : Effect()
    }
}