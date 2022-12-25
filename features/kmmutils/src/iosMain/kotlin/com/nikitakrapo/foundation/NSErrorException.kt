package com.nikitakrapo.foundation

import platform.Foundation.NSError

class NSErrorException(val error: NSError) : Exception() {
    override val message: String = error.localizedDescription
}

fun NSError.toException() = NSErrorException(this)