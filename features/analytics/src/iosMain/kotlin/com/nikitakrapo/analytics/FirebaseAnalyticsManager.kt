package com.nikitakrapo.analytics

import cocoapods.FirebaseAnalytics.FIRAnalytics

class FirebaseAnalyticsManager : AnalyticsManager {
    override fun reportEvent(event: String) {
        FIRAnalytics.logEventWithName(event, null)
    }

    override fun reportAttributedEvent(event: String, attributeBuilder: AttributeSet.() -> Unit) {
        val attrs = AttributeSet()
        attributeBuilder.invoke(attrs)

        FIRAnalytics.logEventWithName(event, attrs.attributes.mapValues { it.value.unpack() })
    }

    private fun AttributeSet.AttributeValue.unpack(): Any = when (this) {
        is AttributeSet.AttributeValue.String -> string
        is AttributeSet.AttributeValue.Int -> int
        is AttributeSet.AttributeValue.Float -> float
        is AttributeSet.AttributeValue.Serializable -> obj
    }
}