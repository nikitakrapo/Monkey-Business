package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.account.BearerTokensProviderImpl
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.monkeybusiness.finance.TransactionsRepositoryImpl
import com.nikitakrapo.monkeybusiness.finances.FinancesDependencies
import com.nikitakrapo.monkeybusiness.finances.TransactionAddRouter
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.profile.ProfileDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class HomeDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val bearerTokensProvider: BearerTokensProvider,
    val transactionAddRouter: TransactionAddRouter,
    val profileEditRouter: ProfileEditRouter,
    val platformContext: PlatformContext,
) {
    fun financesDependencies(): FinancesDependencies {
        val transactionsRepository = TransactionsRepositoryImpl(
            platformContext = platformContext,
            bearerTokensProvider = bearerTokensProvider
        )
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
