package com.nikitakrapo

import java.util.UUID

actual fun randomUuid(): String = UUID.randomUUID().toString()
