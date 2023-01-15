package com.nikitakrapo.monkeybusiness.home

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.monkeybusiness.profile.ProfileDependencies
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class HomeDependencies(
    val analyticsManager: AnalyticsManager,
    val accountManager: AccountManager,
    val profileEditRouter: ProfileEditRouter,
) {
    fun profileDependencies() = ProfileDependencies(
        profileEditRouter = profileEditRouter,
        accountManager = accountManager,
    )
}
