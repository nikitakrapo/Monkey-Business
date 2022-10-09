package com.nikitakrapo.monkeybusiness

import app.cash.turbine.test
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

@OptIn(ExperimentalCoroutinesApi::class)
class CoreComponentTests {

    @BeforeTest
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `correct initial state WHEN Profile initial child`() = runTest {
        val component = component(initialChild = Core.Child.Profile)

        component.state.test {
            assertEquals(state(child = Core.Child.Profile), awaitItem())
        }
    }

    @Test
    fun `correct initial state WHEN Home initial child`() = runTest {
        val component = component(initialChild = Core.Child.Home)

        component.state.test {
            assertEquals(state(child = Core.Child.Home), awaitItem())
        }
    }

    @Test
    fun `navigate home WHEN home clicked and Profile initial child`() = runTest {
        val component = component(initialChild = Core.Child.Profile)
        component.onHomeClicked()

        component.state.test {
            assertEquals(state(child = Core.Child.Profile), awaitItem())
            assertEquals(state(child = Core.Child.Home), awaitItem())
        }
    }

    @Test
    fun `navigate home WHEN home clicked and Home initial child`() = runTest {
        val component = component(initialChild = Core.Child.Home)
        component.onHomeClicked()

        component.state.test {
            assertEquals(state(Core.Child.Home), awaitItem())
        }
    }

    @Test
    fun `navigate profile WHEN profile clicked and Profile initial child`() = runTest {
        val component = component(initialChild = Core.Child.Profile)
        component.onProfileClicked()

        component.state.test {
            assertEquals(state(child = Core.Child.Profile), awaitItem())
        }
    }

    @Test
    fun `navigate profile WHEN profile clicked and Home initial child`() = runTest {
        val component = component(initialChild = Core.Child.Home)
        component.onProfileClicked()

        component.state.test {
            assertEquals(state(child = Core.Child.Home), awaitItem())
            assertEquals(state(child = Core.Child.Profile), awaitItem())
        }
    }

    private fun component(
        initialChild: Core.Child = Core.Child.Home,
    ): Core {
        return CoreComponent(
            initialChild = initialChild,
            featureFactory = LoggingFeatureFactory(FeatureFactory()) {
                println(it)
            }
        )
    }

    private fun state(
        child: Core.Child = Core.Child.Home,
    ): Core.State {
        return Core.State(
            child = child,
        )
    }
}