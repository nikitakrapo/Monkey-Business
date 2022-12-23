package com.nikitakrapo.monkeybusiness

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.compose.ConstraintLayout
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

        // TODO: make proper dependency management (at least component factory)
        coreComponent = CoreComponentImpl(
            componentContext = defaultComponentContext(),
            analytics = CoreScreenAnalytics(mainActivityComponent.analyticsManager)
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
                Surface(color = MaterialTheme.colorScheme.surface) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val (coreScreen, debugButton) = createRefs()

                        CoreScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .constrainAs(coreScreen) {
                                    centerTo(parent)
                                },
                            component = coreComponent
                        )

                        // TODO: make testing abstraction
                        if (BuildConfig.DEBUG) {
                            DebugButton(
                                modifier = Modifier
                                    .constrainAs(debugButton) {
                                        centerVerticallyTo(parent)
                                        end.linkTo(parent.end)
                                    },
                            )
                        }
                    }
                }
            }
        }
    }
}
