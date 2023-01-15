package com.nikitakrapo.foundation

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSError

@Throws(NSErrorException::class)
inline fun <T> getCatchingNSError(block: (errorPointer: CPointer<ObjCObjectVar<NSError?>>) -> T): T {
    memScoped {
        val errorPointed: ObjCObjectVar<NSError?> = alloc()
        val result: T = block(errorPointed.ptr)
        errorPointed.value?.let { throw it.toException() }
        return result
    }
}
