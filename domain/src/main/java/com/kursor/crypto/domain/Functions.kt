package com.kursor.crypto.domain

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

suspend fun <R> tryRequest(block: suspend () -> R): Result<R> = withContext(Dispatchers.IO) {
    runCatching {
        block()
    }.onFailure {
        if (it is ConnectionException || it is IOException) {
            it.printStackTrace()
        } else throw it
    }
}