package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.monkeybusiness.finance.account.BankAccountsRepository
import com.nikitakrapo.monkeybusiness.finances.FinancesDependencies
import com.nikitakrapo.monkeybusiness.finances.accounts.ProductOpeningLandingRouter
import com.nikitakrapo.monkeybusiness.profile.ProfileDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class HomeDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val productOpeningLandingRouter: ProductOpeningLandingRouter,
    val profileEditRouter: ProfileEditRouter,
    val bankAccountsRepository: BankAccountsRepository,
) {
    fun financesDependencies(): FinancesDependencies {
        return FinancesDependencies(
            productOpeningLandingRouter = productOpeningLandingRouter,
            bankAccountsRepository = bankAccountsRepository,
        )
    }

    fun profileDependencies() = ProfileDependencies(
        profileEditRouter = profileEditRouter,
        accountManager = accountManager,
    )
}
