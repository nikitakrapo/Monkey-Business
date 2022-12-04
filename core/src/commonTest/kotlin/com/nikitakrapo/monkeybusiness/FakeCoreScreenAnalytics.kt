package com.nikitakrapo.monkeybusiness

import com.nikitakrapo.analytics.AnalyticsManager
import com.nikitakrapo.analytics.AttributeSet

// TODO: change to mock
fun FakeCoreScreenAnalytics() = CoreScreenAnalytics(object : AnalyticsManager {
    override fun reportEvent(event: String) {}
    override fun reportAttributedEvent(event: String, attributeBuilder: AttributeSet.() -> Unit) {}
})
