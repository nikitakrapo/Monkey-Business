package com.nikitakrapo

import platform.Foundation.NSUUID

actual fun randomUuid(): String = NSUUID().UUIDString
