package com.kursor.crypto.ui

import com.kursor.crypto.ui.Screens.Args.CRYPTO_ID
import com.kursor.crypto.ui.Screens.Args.CRYPTO_IMAGE_LINK
import com.kursor.crypto.ui.Screens.Args.CRYPTO_NAME

sealed class Screens(val start: String, vararg args: String) {

    val route = start + args.joinToString("") { "/{$it}" }

    object CryptoCurrencyInfoListScreen : Screens(start = "CryptoCurrencyInfoListScreen")

    object CryptoCurrencyDescriptionScreen : Screens(
        start = "CryptoCurrencyDescriptionScreen",
        CRYPTO_ID, CRYPTO_NAME, CRYPTO_IMAGE_LINK
    ) {
        fun withArgs(cryptoId: String, cryptoName: String, cryptoImageLink: String) =
            buildPath(cryptoId, cryptoName, cryptoImageLink.replace("/", """\"""))
    }

    object Args {
        const val CRYPTO_ID = "cryptoId"
        const val CRYPTO_NAME = "cryptoName"
        const val CRYPTO_IMAGE_LINK = "cryptoImageLink"
    }

    protected fun buildPath(vararg args: String) = buildString {
        append(start)
        args.forEach {
            append("/$it")
        }
    }
}