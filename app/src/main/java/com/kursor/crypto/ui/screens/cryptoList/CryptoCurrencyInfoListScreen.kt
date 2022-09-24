package com.kursor.crypto.ui.screens.cryptoList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.kursor.crypto.ConnectionStatus
import com.kursor.crypto.R
import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import com.kursor.crypto.ui.screens.LoadingScreen
import com.kursor.crypto.ui.screens.SomethingWentWrongSceeen
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListViewModel.Currency
import com.kursor.crypto.ui.screens.elements.ChipGroup
import org.koin.androidx.compose.getViewModel

@Composable
fun CryptoCurrencyInfoListScreen(
    navController: NavController,
    viewModel: CryptoCurrencyInfoListViewModel = getViewModel()
) {

    val cryptoCurrencyInfoList =
        viewModel.cryptoCurrencyInfoListLiveData.observeAsState(initial = emptyList())

    val connectionStatus =
        viewModel.connectionStatusLiveData.observeAsState(initial = ConnectionStatus.LOADING)

    viewModel.loadData(Currency.USD)

    Scaffold(
        topBar = {
            TopAppBar() {
                Column() {
                    Text(
                        text = stringResource(id = R.string.crypto_currency_list),
                        modifier = Modifier.padding(8.dp)
                    )
                    ChipGroup(
                        elements = Currency.values().toList(),
                        onSelectedChanged = {
                            viewModel.loadData(it)
                        },
                        selected = Currency.USD
                    )
                }

            }
        }
    ) {
        when (connectionStatus.value) {
            ConnectionStatus.LOADING -> LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
            ConnectionStatus.SUCCESS -> {
                LazyColumn {
                    items(cryptoCurrencyInfoList.value) {
                        CryptoCurrencyInfoListItem(cryptoCurrencyInfo = it)
                    }
                }
            }
            ConnectionStatus.FAILURE -> SomethingWentWrongSceeen(
                modifier = Modifier.fillMaxSize()
            ) {
                viewModel.loadData(Currency.USD)
            }
        }

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