package com.kursor.crypto.ui

import com.kursor.crypto.ui.Screens.Args.CRYPTO_ID

sealed class Screens(val start: String, vararg args: String) {

    val route = start + args.joinToString("") { "/{$it}" }

    object CryptoCurrencyInfoListScreen : Screens(start = "/cryptoCurrencyInfoList")

    object CryptoCurrencyDescriptionScreen : Screens(
        start = "/cryptoCurrencyDescription",
        CRYPTO_ID
    ) {
        fun withArgs(cryptoId: String) = buildPath(cryptoId)
    }

    object Args {
        const val CRYPTO_ID = "cryptoId"
    }

    protected fun buildPath(vararg args: String) = buildString {
        append(start)
        args.forEach {
            append("/$it")
        }
    }
}