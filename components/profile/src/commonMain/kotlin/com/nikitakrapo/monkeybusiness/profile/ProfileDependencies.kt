package com.nikitakrapo.monkeybusiness.profile

import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.monkeybusiness.profile.edit.ProfileEditRouter

class ProfileDependencies(
    val profileEditRouter: ProfileEditRouter,
    val accountManager: AccountManager,
)
