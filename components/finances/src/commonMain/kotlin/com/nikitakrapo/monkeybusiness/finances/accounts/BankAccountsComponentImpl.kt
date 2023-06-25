package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.nikitakrapo.decompose.coroutines.coroutineScope
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

class BankAccountsComponentImpl(
    componentContext: ComponentContext,
    storeFactory: StoreFactory = DefaultStoreFactory(),
    dependencies: BankAccountsDependencies,
) : BankAccountsComponent, ComponentContext by componentContext {

    private val componentScope = coroutineScope(Dispatchers.Main)

    private val bankAccountsRepository = dependencies.bankAccountsRepository
    private val productOpeningLandingRouter = dependencies.productOpeningLandingRouter

    private val store = BankAccountsStore(
        bankAccountsRepository = bankAccountsRepository,
        storeFactory = storeFactory,
        coroutineContext = componentScope.coroutineContext,
    )

    override val state: StateFlow<State> = store.stateFlow

    override fun onAccountClicked(index: Int) {
        TODO()
    }

    override fun onOpenProductClicked() {
        productOpeningLandingRouter.openProductOpening()
    }
}