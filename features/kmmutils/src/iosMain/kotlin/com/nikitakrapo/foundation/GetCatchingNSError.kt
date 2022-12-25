package com.nikitakrapo.foundation

import kotlinx.cinterop.CPointer
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSError

inline fun <T> getCatchingNSError(block: (errorPointer: CPointer<ObjCObjectVar<NSError?>>) -> T): Result<T> {
    memScoped {
        val errorPointed: ObjCObjectVar<NSError?> = alloc()
        val result: T = block(errorPointed.ptr)
        return errorPointed.value?.let {
            Result.failure(it.toException())
        } ?: Result.success(result)
    }
}
