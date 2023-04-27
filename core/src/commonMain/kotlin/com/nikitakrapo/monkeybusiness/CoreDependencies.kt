package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.account.BearerTokenProviderImpl
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.monkeybusiness.finance.account.BankAccountsRepositoryFactory
import com.nikitakrapo.monkeybusiness.finances.accounts.ProductOpeningLandingRouter
import com.nikitakrapo.monkeybusiness.finances.accounts.opening.BankAccountOpeningDependencies
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningDependencies
import com.nikitakrapo.monkeybusiness.finances.products.ProductOpeningRouter
import com.nikitakrapo.monkeybusiness.home.HomeDependencies
import com.nikitakrapo.monkeybusiness.network.HttpClientFactory
import com.nikitakrapo.monkeybusiness.network.auth.BearerTokenProvider
import com.nikitakrapo.monkeybusiness.profile.auth.AuthDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class CoreDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val platformContext: PlatformContext,
) {
    private val bearerTokenProvider: BearerTokenProvider =
        BearerTokenProviderImpl(accountManager)

    private val httpClientFactory: HttpClientFactory =
        HttpClientFactory(bearerTokenProvider)

    private val bankAccountsRepository by lazy {
        BankAccountsRepositoryFactory.create(
            httpClient = httpClientFactory.mainClient,
        )
    }

    fun homeDependencies(
        productOpeningLandingRouter: ProductOpeningLandingRouter,
        profileEditRouter: ProfileEditRouter,
    ) = HomeDependencies(
        analyticsManager = analyticsManager,
        accountManager = accountManager,
        profileEditRouter = profileEditRouter,
        productOpeningLandingRouter = productOpeningLandingRouter,
        bankAccountsRepository = bankAccountsRepository,
    )

    fun bankAccountOpeningDependencies(): BankAccountOpeningDependencies =
        BankAccountOpeningDependencies(
            bankAccountsRepository = bankAccountsRepository,
        )

    fun authDependencies() = AuthDependencies(
        accountManager = accountManager,
    )

    fun profileEditDependencies() = ProfileEditDependencies(
        accountManager = accountManager,
    )

    fun productOpeningDependencies(
        productOpeningRouter: ProductOpeningRouter,
    ) = ProductOpeningDependencies(
        analyticsManager = analyticsManager,
        productOpeningRouter = productOpeningRouter,
    )
}
