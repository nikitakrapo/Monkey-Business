package com.nikitakrapo.monkeybusiness.finances.accounts

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.nikitakrapo.monkeybusiness.finance.account.BankAccountsRepository
import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent.State
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal fun BankAccountsStore(
    bankAccountsRepository: BankAccountsRepository,
    storeFactory: StoreFactory,
    coroutineContext: CoroutineContext,
) = storeFactory.create(
    name = "BankAccounts",
    initialState = State(
        accountList = null,
        isLoading = true,
        error = null,
    ),
    reducer = {
        when (it) {
            is Message.AccountListUpdated -> copy(
                accountList = it.accountList,
                isLoading = false,
                error = null,
            )

            is Message.AccountListUpdateFailed -> copy(
                isLoading = false,
                error = it.error.message ?: "Unknown error"
            )
        }
    },
    executorFactory = {
        object : CoroutineExecutor<Intent, Action, State, Message, Nothing>(coroutineContext) {
            override fun executeAction(action: Action, getState: () -> State) {
                when (action) {
                    is Action.AccountListUpdated -> {
                        val message = Message.AccountListUpdated(action.accountList)
                        dispatch(message)
                    }

                    is Action.AccountListUpdateFailed -> {
                        val message = Message.AccountListUpdateFailed(action.reason)
                        dispatch(message)
                    }
                }
            }
        }
    },
    bootstrapper = object : CoroutineBootstrapper<Action>(coroutineContext) {
        override fun invoke() {
            scope.launch {
                val currentAccounts = bankAccountsRepository.fetchBankAccounts()
                    .onFailure {
                        val failAction = Action.AccountListUpdateFailed(it)
                        dispatch(failAction)
                    }
                    .getOrNull()
                bankAccountsRepository.getBankAccounts()
                    .onStart { currentAccounts?.let { emit(it) } }
                    .map(Action::AccountListUpdated)
                    .collect(::dispatch)
            }
        }
    },
)


internal sealed class Intent

internal sealed class Action {
    class AccountListUpdated(val accountList: List<BankAccount>) : Action()
    class AccountListUpdateFailed(val reason: Throwable) : Action()
}

internal sealed class Message {
    class AccountListUpdated(val accountList: List<BankAccount>) : Message()
    class AccountListUpdateFailed(val error: Throwable) : Message()
}