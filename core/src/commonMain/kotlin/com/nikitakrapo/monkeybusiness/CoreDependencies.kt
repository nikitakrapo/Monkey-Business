package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.account.BearerTokensProviderImpl
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.monkeybusiness.finance.transactions.TransactionsRepositoryImpl
import com.nikitakrapo.monkeybusiness.finances.accounts.ProductOpeningLandingRouter
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningDependencies
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningRouter
import com.nikitakrapo.monkeybusiness.finances.transactions.TransactionAddDependencies
import com.nikitakrapo.monkeybusiness.home.HomeDependencies
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokensProvider
import com.nikitakrapo.monkeybusiness.profile.auth.AuthDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class CoreDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val platformContext: PlatformContext,
) {
    private val bearerTokensProvider: BearerTokensProvider =
        BearerTokensProviderImpl(accountManager)
    private val transactionsRepository = TransactionsRepositoryImpl(
        platformContext = platformContext,
        bearerTokensProvider = bearerTokensProvider,
    )

    fun homeDependencies(
        productOpeningLandingRouter: ProductOpeningLandingRouter,
        profileEditRouter: ProfileEditRouter,
    ) = HomeDependencies(
        analyticsManager = analyticsManager,
        accountManager = accountManager,
        profileEditRouter = profileEditRouter,
        productOpeningLandingRouter = productOpeningLandingRouter,
    )

    fun authDependencies() = AuthDependencies(
        accountManager = accountManager,
    )

    fun profileEditDependencies() = ProfileEditDependencies(
        accountManager = accountManager,
    )

    fun transactionAddDependencies() = TransactionAddDependencies(
        transactionsRepository = transactionsRepository,
    )

    fun productOpeningDependencies(
        productOpeningRouter: ProductOpeningRouter,
    ) = ProductOpeningDependencies(
        analyticsManager = analyticsManager,
        productOpeningRouter = productOpeningRouter,
    )
}
