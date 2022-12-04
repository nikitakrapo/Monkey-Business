package com.nikitakrapo.monkeybusiness

import app.cash.turbine.test
import com.nikitakrapo.component.TestComponentContext
import com.nikitakrapo.monkeybusiness.CoreComponentImpl.CoreScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class CoreComponentTests {

    @BeforeTest
    fun setUp() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
    }

    @Test
    fun `correct initial state WHEN more initial child`() = runTest {
        val component = component(initialChild = CoreScreen.More)

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.More }
        }
    }

    @Test
    fun `correct initial state WHEN Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.Home)

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate home WHEN home clicked and more initial child`() = runTest {
        val component = component(initialChild = CoreScreen.More)
        component.onHomeClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate home WHEN home clicked and Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.Home)
        component.onHomeClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate more WHEN more clicked and more initial child`() = runTest {
        val component = component(initialChild = CoreScreen.More)
        component.onMoreClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.More }
        }
    }

    @Test
    fun `navigate more WHEN more clicked and Home initial child`() = runTest {
        val component = component(initialChild = CoreScreen.Home)
        component.onMoreClicked()

        component.child.test {
            assertTrue { awaitItem() is CoreComponent.Child.More }
        }
    }

    private fun component(
        initialChild: CoreScreen = CoreScreen.Home
    ): CoreComponent {
        return CoreComponentImpl(
            componentContext = TestComponentContext(),
            initialScreen = initialChild,
            analytics = FakeCoreScreenAnalytics()
        )
    }
}
