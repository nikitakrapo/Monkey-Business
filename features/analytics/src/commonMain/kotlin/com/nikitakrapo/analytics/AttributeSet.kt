package com.nikitakrapo.analytics

import kotlinx.serialization.Serializable

class AttributeSet internal constructor() {
    internal val attributes = mutableMapOf<String, AttributeValue>()

    fun put(key: String, value: String) {
        attributes[key] = AttributeValue.String(value)
    }

    fun put(key: String, value: Int) {
        attributes[key] = AttributeValue.Int(value)
    }

    fun put(key: String, value: Float) {
        attributes[key] = AttributeValue.Float(value)
    }

    fun put(key: String, value: @Serializable Any) {
        attributes[key] = AttributeValue.Serializable(value)
    }

    internal sealed class AttributeValue {
        class String(val string: kotlin.String) : AttributeValue()
        class Int(val int: kotlin.Int) : AttributeValue()
        class Float(val float: kotlin.Float) : AttributeValue()
        class Serializable(val obj: @kotlinx.serialization.Serializable Any) : AttributeValue()
    }
}