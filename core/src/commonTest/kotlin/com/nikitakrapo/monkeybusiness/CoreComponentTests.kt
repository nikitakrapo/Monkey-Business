package com.nikitakrapo.monkeybusiness

import app.cash.turbine.test
import com.nikitakrapo.account.testAccountManager
import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.analytics.AttributeSet
import com.nikitakrapo.decompose.TestComponentContext
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
    fun `correct initial state`() = runTest {
        val component = component()

        component.childStack.test {
            assertTrue { awaitItem() is CoreComponent.Child.More }
        }
    }

    @Test
    fun `navigate home WHEN home clicked`() = runTest {
        val component = component()
        component.onHomeClicked()

        component.childStack.test {
            assertTrue { awaitItem() is CoreComponent.Child.Home }
        }
    }

    @Test
    fun `navigate more WHEN more clicked`() = runTest {
        val component = component()
        component.onMoreClicked()

        component.childStack.test {
            assertTrue { awaitItem() is CoreComponent.Child.More }
        }
    }

    private fun component(): CoreComponent {
        return CoreComponentImpl(
            componentContext = TestComponentContext(),
            analyticsManager = object : AnalyticsManager {
                override fun reportEvent(event: String) {}
                override fun reportAttributedEvent(event: String, attributeBuilder: AttributeSet.() -> Unit) {}
            },
            accountManager = testAccountManager()
        )
    }
}
