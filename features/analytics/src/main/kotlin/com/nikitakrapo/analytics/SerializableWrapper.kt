package com.nikitakrapo.analytics

class SerializableWrapper(val value: @kotlinx.serialization.Serializable Any) : java.io.Serializable
