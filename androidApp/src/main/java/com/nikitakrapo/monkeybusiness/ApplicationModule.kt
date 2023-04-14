package com.nikitakrapo.monkeybusiness

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.nikitakrapo.account.AccountManager
import com.nikitakrapo.account.AccountManagerImpl
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.analytics.FirebaseAnalyticsManager
import org.koin.dsl.module

fun applicationModule(context: Context) = module {
    FirebaseApp.initializeApp(context)
    FirebaseAnalytics.getInstance(context)
    val analyticsManager = FirebaseAnalyticsManager(FirebaseAnalytics.getInstance(context))
    single<AnalyticsManager> { analyticsManager }
    single<AccountManager> { AccountManagerImpl(analyticsManager) }
}
