package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.monkeybusiness.profile.ProfileDependencies

class HomeDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
) {
    fun profileDependencies() = ProfileDependencies(
        accountManager = accountManager,
    )
}
