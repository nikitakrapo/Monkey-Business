package com.nikitakrapo.analytics

interface AnalyticsManager {
    fun reportEvent(event: String)
    fun reportAttributedEvent(event: String, attributeBuilder: AttributeSet.() -> Unit)
}
