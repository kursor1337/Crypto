package com.kursor.crypto.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kursor.crypto.ui.Screens
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

//        composable(
//            route = Screens.CryptoCurrencyDescriptionScreen.route,
//            arguments = listOf(
//                navArgument(CRYPTO_ID) { type = NavType.StringType }
//            )
//        ) {
//            CryptoCurrencyDescriptionScreen(
//                navController = navController,
//
//            )
//        }

    }

}