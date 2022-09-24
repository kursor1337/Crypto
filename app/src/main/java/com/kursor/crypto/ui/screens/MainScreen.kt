package com.kursor.crypto.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kursor.crypto.ui.Screens
import com.kursor.crypto.ui.Screens.Args.CRYPTO_ID
import com.kursor.crypto.ui.Screens.Args.CRYPTO_IMAGE_LINK
import com.kursor.crypto.ui.Screens.Args.CRYPTO_NAME
import com.kursor.crypto.ui.screens.cryptoDescription.CryptoCurrencyDescriptionScreen
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListItem
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.CryptoCurrencyInfoListScreen.route
    ) {

        composable(Screens.CryptoCurrencyInfoListScreen.route) {
            CryptoCurrencyInfoListScreen(navController = navController)
        }
        Log.d("NavHost", Screens.CryptoCurrencyDescriptionScreen.route)

        composable(
            route = Screens.CryptoCurrencyDescriptionScreen.route,
            arguments = listOf(
                navArgument(CRYPTO_ID) { type = NavType.StringType },
                navArgument(CRYPTO_NAME) { type = NavType.StringType },
                navArgument(CRYPTO_IMAGE_LINK) { type = NavType.StringType }
            )
        ) {

            val cryptoId = it.arguments!!.getString(CRYPTO_ID)!!
            val cryptoName = it.arguments!!.getString(CRYPTO_NAME)!!
            val cryptoImageLink =
                it.arguments!!.getString(CRYPTO_IMAGE_LINK)!!.replace("""\""", "/")

            CryptoCurrencyDescriptionScreen(
                cryptoId = cryptoId,
                cryptoName = cryptoName,
                cryptoImageLink = cryptoImageLink,
                navController = navController
            )
        }

    }

}