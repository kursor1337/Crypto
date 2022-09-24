package com.kursor.crypto.ui.screens.cryptoDescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.kursor.crypto.ConnectionStatus
import com.kursor.crypto.R
import com.kursor.crypto.ui.screens.LoadingScreen
import com.kursor.crypto.ui.screens.SomethingWentWrongScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun CryptoCurrencyDescriptionScreen(
    cryptoId: String,
    cryptoName: String,
    cryptoImageLink: String,
    navController: NavController,
    viewModel: CryptoCurrencyDescriptionViewModel = getViewModel()
) {

    val cryptoCurrencyDescription = viewModel.cryptoCurrencyDescriptionLiveData.observeAsState()

    val connectionStatus =
        viewModel.connectionStatusLiveData.observeAsState(ConnectionStatus.LOADING)

    viewModel.loadData(cryptoId)

    Scaffold(
        topBar = {
            TopAppBar {
                IconButton(
                    modifier = Modifier.padding(12.dp),
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
                Text(
                    text = cryptoName,
                    modifier = Modifier.padding(
                        vertical = 16.dp,
                        horizontal = 16.dp
                    ),
                    style = TextStyle(
                        fontSize = 22.sp
                    ),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    ) {

        when (connectionStatus.value) {
            ConnectionStatus.LOADING -> LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
            ConnectionStatus.SUCCESS -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(cryptoImageLink),
                        contentDescription = "crypto icon"
                    )

                    TextBlock(
                        title = stringResource(id = R.string.crypto_currency_description),
                        text = cryptoCurrencyDescription.value?.description ?: "",
                        modifier = Modifier.padding(12.dp)
                    )

                    TextBlock(
                        title = stringResource(id = R.string.crypto_currency_categories),
                        text = cryptoCurrencyDescription.value?.categories?.joinToString() ?: "",
                        modifier = Modifier.padding(12.dp)
                    )
                }

            }
            ConnectionStatus.FAILURE -> SomethingWentWrongScreen(
                modifier = Modifier.fillMaxSize()
            ) {
                viewModel.loadData(cryptoId)
            }
        }

    }
}

@Composable
fun TextBlock(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 22.sp
            )
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Start,
        )
    }
}