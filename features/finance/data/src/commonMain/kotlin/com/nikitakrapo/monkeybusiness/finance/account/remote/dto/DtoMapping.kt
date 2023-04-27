package com.nikitakrapo.monkeybusiness.finance.account.remote.dto

import com.nikitakrapo.monkeybusiness.finance.models.BankAccount
import com.nikitakrapo.monkeybusiness.finance.models.BankCard
import com.nikitakrapo.monkeybusiness.finance.models.Currency

internal fun BankAccountDto.toDomainModel() = BankAccount(
    iban = iban,
    name = name,
    balance = balance,
    currency = Currency.fromCode(currencyCode),
    cards = cards.map(BankCardDto::toDomainModel),
)

internal fun BankCardDto.toDomainModel() = BankCard(
    pan = pan,
)