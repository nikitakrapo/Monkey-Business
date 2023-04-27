package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finance.account.BankAccountsRepository
import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsDependencies
import com.nikitakrapo.monkeybusiness.finances.accounts.ProductOpeningLandingRouter

class FinancesDependencies(
    val productOpeningLandingRouter: ProductOpeningLandingRouter,
    val bankAccountsRepository: BankAccountsRepository,
) {
    fun bankAccountsDependencies() = BankAccountsDependencies(
        productOpeningLandingRouter = productOpeningLandingRouter,
        bankAccountsRepository = bankAccountsRepository,
    )
}
