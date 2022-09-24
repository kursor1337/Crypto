package com.kursor.crypto.ui.screens.cryptoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kursor.crypto.R
import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun CryptoCurrencyInfoListScreen(
    navController: NavController,
    viewModel: CryptoCurrencyInfoListViewModel = getViewModel()
) {

    val cryptoCurrencyInfoList =
        viewModel.cryptoCurrencyInfoListLiveData.observeAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar() {
                Column() {
                    Text(text = stringResource(id = R.string.crypto_currency_list))
                }

            }
        }
    ) {

    }

}

@Composable
fun CryptoCurrencyInfoListItem(
    cryptoCurrencyInfo: CryptoCurrencyInfo,
    modifier: Modifier = Modifier
) {

    Row(modifier = modifier) {
        Image(
            painter = rememberImagePainter(cryptoCurrencyInfo.image),
            contentDescription = "crypto image"
        )
        Column {
            Text(
                text = cryptoCurrencyInfo.name,
                modifier = Modifier.padding(4.dp)
            )
            Text(
                text = cryptoCurrencyInfo.symbol.uppercase(),
                modifier = Modifier.padding(4.dp)
            )
        }
    }

}