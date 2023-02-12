package com.nikitakrapo.monkeybusiness

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MonkeyApplication : Application() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Napier.i("Initializing application")
        Napier.base(DebugAntilog())
        scope.launch {
            FirebaseApp.initializeApp(this@MonkeyApplication)
            FirebaseAnalytics.getInstance(this@MonkeyApplication)
        }
    }
}
