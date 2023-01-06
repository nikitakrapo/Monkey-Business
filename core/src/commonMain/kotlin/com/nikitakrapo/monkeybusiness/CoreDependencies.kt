package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.monkeybusiness.home.HomeDependencies
import com.nikitakrapo.monkeybusiness.profile.auth.AuthDependencies

class CoreDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
) {
    fun homeDependencies() = HomeDependencies(
        analyticsManager = analyticsManager,
        accountManager = accountManager,
    )

    fun authDependencies() = AuthDependencies(
        accountManager = accountManager,
    )
}
