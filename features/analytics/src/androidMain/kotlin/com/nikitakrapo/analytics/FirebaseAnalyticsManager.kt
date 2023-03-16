package com.nikitakrapo.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.nikitakrapo.analytics.firebase.checkEventName

class FirebaseAnalyticsManager(
    private val firebaseAnalytics: FirebaseAnalytics,
) : AnalyticsManager {

    override fun reportEvent(event: String) {
        checkEventName(event)

        firebaseAnalytics.logEvent(event, null)
    }

    override fun reportAttributedEvent(event: String, attributeBuilder: AttributeSet.() -> Unit) {
        checkEventName(event)

        val attrs = AttributeSet()
        attributeBuilder.invoke(attrs)

        firebaseAnalytics.logEvent(event, attrs.toBundle())
    }

    private fun AttributeSet.toBundle(): Bundle {
        val bundle = Bundle()
        attributes.forEach { (key, value) ->
            when (value) {
                is AttributeSet.AttributeValue.String -> bundle.putString(key, value.string)
                is AttributeSet.AttributeValue.Int -> bundle.putInt(key, value.int)
                is AttributeSet.AttributeValue.Float -> bundle.putFloat(key, value.float)
                is AttributeSet.AttributeValue.Serializable -> TODO("not yet implemented")
            }
        }
        return bundle
    }
}
