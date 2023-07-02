package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class ProfileDependencies(
    val settingsRouter: SettingsRouter,
    val profileEditRouter: ProfileEditRouter,
    val accountManager: AccountManager,
)
