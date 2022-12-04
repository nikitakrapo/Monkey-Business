package com.nikitakrapo.monkeybusiness

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.analytics.FirebaseAnalytics
import com.nikitakrapo.analytics.FirebaseAnalyticsManager
import com.nikitakrapo.monkeybusiness.design.theme.MonkeyTheme
import com.nikitakrapo.monkeybusiness.navigation.defaultComponentContext

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityComponent: MainActivityComponent
    private lateinit var coreComponent: CoreComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainActivityComponent = MainActivityComponent(
            analyticsManager = FirebaseAnalyticsManager(FirebaseAnalytics.getInstance(this))
        )

        //TODO: make proper dependency management (at least component factory)
        coreComponent = CoreComponentImpl(
            componentContext = defaultComponentContext(),
            analytics = CoreScreenAnalytics(mainActivityComponent.analyticsManager),
        )

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = !isSystemInDarkTheme()

            LaunchedEffect(systemUiController, useDarkIcons) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons,
                    isNavigationBarContrastEnforced = false
                )
            }

            MonkeyTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.statusBars.asPaddingValues()),
                ) {
                    CoreScreen(
                        modifier = Modifier
                            .fillMaxSize(),
                        component = coreComponent,
                    )

                    //TODO: make testing abstraction
                    if (BuildConfig.DEBUG) {
                        DebugButton()
                    }
                }
            }
        }
    }
}
