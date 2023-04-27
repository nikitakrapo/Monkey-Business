package com.nikitakrapo.monkeybusiness.finances.accounts

import com.nikitakrapo.monkeybusiness.finance.account.BankAccountsRepository

class BankAccountsDependencies(
    val productOpeningLandingRouter: ProductOpeningLandingRouter,
    val bankAccountsRepository: BankAccountsRepository,
)