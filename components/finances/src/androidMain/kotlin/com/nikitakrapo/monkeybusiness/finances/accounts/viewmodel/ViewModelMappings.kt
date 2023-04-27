package com.nikitakrapo.monkeybusiness.finances.accounts.viewmodel

import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.BankCard
import com.nikitakrapo.monkeybusiness.finance.utils.MoneyAmountTextFactory
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsComponent

internal fun BankAccountsComponent.State.toViewState(): BankAccountsScreenViewState =
    if (accountList != null) {
        BankAccountsScreenViewState.ShowingAccounts(
            accountList.map(BankAccount::toViewState)
        )
    } else {
        BankAccountsScreenViewState.Loading
    }

internal fun BankAccount.toViewState() = BankAccountViewState(
    name = name,
    moneyText = MoneyAmountTextFactory.createMoneyAmountText(
        currency = currency,
        amount = balance,
    ),
    currencySign = currency.symbol,
    bankCardList = cards.map(BankCard::toViewState),
)

private const val SmallCardDisplayedDigits = 4

internal fun BankCard.toViewState() = SmallBankCardViewState(
    text = pan.takeLast(SmallCardDisplayedDigits),
)