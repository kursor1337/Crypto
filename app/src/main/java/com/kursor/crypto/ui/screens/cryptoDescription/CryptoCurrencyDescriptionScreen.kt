package com.kursor.crypto.ui.screens.cryptoDescription

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(
                        modifier = Modifier.padding(6.dp),
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                    Text(
                        text = cryptoName,
                        modifier = Modifier.padding(
                            vertical = 12.dp,
                            horizontal = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Divider(
                    modifier = Modifier.height(3.dp)
                )
            }
        }
    ) {
        when (connectionStatus.value) {
            ConnectionStatus.LOADING -> LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
            ConnectionStatus.SUCCESS -> {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Image(
                            painter = rememberAsyncImagePainter(cryptoImageLink),
                            contentDescription = "crypto icon",
                            modifier = Modifier
                                .padding(12.dp)
                                .size(120.dp)
                        )

                        TextBlock(
                            title = stringResource(id = R.string.crypto_currency_description),
                            text = cryptoCurrencyDescription.value?.description?.en ?: "",
                            modifier = Modifier.padding(6.dp)
                        )

                        TextBlock(
                            title = stringResource(id = R.string.crypto_currency_categories),
                            text = cryptoCurrencyDescription.value?.categories?.joinToString()
                                ?: "",
                            modifier = Modifier.padding(6.dp)
                        )
                    }
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
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                ),
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
                .padding(
                    vertical = 4.dp,
                    horizontal = 12.dp
                ),
            textAlign = TextAlign.Start,
        )
    }
}