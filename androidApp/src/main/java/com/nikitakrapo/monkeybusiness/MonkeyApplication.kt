package com.nikitakrapo.monkeybusiness

import android.app.Application
import com.nikitakrapo.monkeybusiness.logging.KoinNapierLogger
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MonkeyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())
        Napier.i("MonkeyApplication.onCreate()")

        startKoin {
            androidContext(this@MonkeyApplication)
            logger(KoinNapierLogger())
            modules(applicationModule(this@MonkeyApplication))
        }
    }
}
