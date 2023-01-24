package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.application.PlatformContext
import com.nikitakrapo.monkeybusiness.home.HomeDependencies
import com.nikitakrapo.monkeybusiness.profile.auth.AuthDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class CoreDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val platformContext: PlatformContext,
) {
    fun homeDependencies(
        profileEditRouter: ProfileEditRouter
    ) = HomeDependencies(
        analyticsManager = analyticsManager,
        accountManager = accountManager,
        profileEditRouter = profileEditRouter,
    )

    fun authDependencies() = AuthDependencies(
        accountManager = accountManager,
    )
}
