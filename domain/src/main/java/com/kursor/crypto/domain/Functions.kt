package com.kursor.crypto.domain

inline fun <R> tryRequest(block: () -> R): Result<R> = runCatching {
    block()
}.onFailure {
    if (it !is ConnectionException) throw it
}