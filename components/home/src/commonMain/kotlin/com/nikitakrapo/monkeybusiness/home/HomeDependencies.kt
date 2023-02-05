package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.monkeybusiness.finance.TransactionsRepository
import com.nikitakrapo.monkeybusiness.finances.FinancesDependencies
import com.nikitakrapo.monkeybusiness.finances.TransactionAddRouter
import com.nikitakrapo.monkeybusiness.profile.ProfileDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class HomeDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val transactionsRepository: TransactionsRepository,
    val transactionAddRouter: TransactionAddRouter,
    val profileEditRouter: ProfileEditRouter,
) {
    fun financesDependencies(): FinancesDependencies {
        return FinancesDependencies(
            transactionsRepository = transactionsRepository,
            transactionAddRouter = transactionAddRouter,
        )
    }

    fun profileDependencies() = ProfileDependencies(
        profileEditRouter = profileEditRouter,
        accountManager = accountManager,
    )
}
