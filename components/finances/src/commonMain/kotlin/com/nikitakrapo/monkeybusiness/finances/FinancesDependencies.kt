package com.nikitakrapo.monkeybusiness.finances

import com.nikitakrapo.monkeybusiness.finances.accounts.BankAccountsDependencies
import com.nikitakrapo.monkeybusiness.finances.accounts.ProductOpeningLandingRouter

class FinancesDependencies(
    val productOpeningLandingRouter: ProductOpeningLandingRouter,
) {
    fun bankAccountsDependencies() = BankAccountsDependencies(
        productOpeningLandingRouter = productOpeningLandingRouter,
    )
}
