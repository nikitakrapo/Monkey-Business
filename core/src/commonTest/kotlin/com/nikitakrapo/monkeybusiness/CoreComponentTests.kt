package com.nikitakrapo.monkeybusiness

import app.cash.turbine.test
import com.nikitakrapo.monkeybusiness.CoreComponentImpl.CoreScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import ru.yandex.lavka.mvi.feature.FeatureFactory
import ru.yandex.lavka.mvi.feature.logging.LoggingFeatureFactory
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CoreComponentTests {

    @BeforeTest
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `correct initial state WHEN Profile initial child`() = runTest {
        val component = component(initialChild = CoreScreen.PROFILE)

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Profile }
        }
    }

    @Test
    fun `correct initial state WHEN Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.HOME)

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate home WHEN home clicked and Profile initial child`() = runTest {
        val component = component(initialChild = CoreScreen.PROFILE)
        component.onHomeClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate home WHEN home clicked and Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.HOME)
        component.onHomeClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate profile WHEN profile clicked and Profile initial child`() = runTest {
        val component = component(initialChild = CoreScreen.PROFILE)
        component.onProfileClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Profile }
        }
    }

    @Test
    fun `navigate profile WHEN profile clicked and Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.HOME)
        component.onProfileClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Profile }
        }
    }

    private fun component(
        initialChild: CoreScreen = CoreScreen.HOME,
    ): CoreComponent {
        return CoreComponentImpl(
            initialScreen = initialChild,
        )
    }
}