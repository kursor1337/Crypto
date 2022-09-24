package com.kursor.crypto.ui.screens.cryptoList

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kursor.crypto.ConnectionStatus
import com.kursor.crypto.R
import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import com.kursor.crypto.ui.Screens
import com.kursor.crypto.ui.screens.LoadingScreen
import com.kursor.crypto.ui.screens.SomethingWentWrongScreen
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListViewModel.Currency
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListViewModel.RefreshingState
import com.kursor.crypto.ui.screens.elements.ChipGroup
import org.koin.androidx.compose.getViewModel
import java.text.DecimalFormat
import kotlin.math.sign
import es.dmoral.toasty.Toasty

@Composable
fun CryptoCurrencyInfoListScreen(
    navController: NavController,
    viewModel: CryptoCurrencyInfoListViewModel = getViewModel()
) {

    val cryptoCurrencyInfoList =
        viewModel.cryptoCurrencyInfoListLiveData.observeAsState(initial = emptyList())
    val connectionStatus =
        viewModel.connectionStatusLiveData.observeAsState(initial = ConnectionStatus.LOADING)
    val selectedCurrency = viewModel.selectedCurrencyLiveData.observeAsState(initial = Currency.USD)
    val refreshingState = viewModel.refreshingLiveData.observeAsState(false)

    val context = LocalContext.current

    viewModel.loadData(Currency.USD)

    Scaffold(
        topBar = {
            Surface(
                color = Color.White
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.crypto_currency_list),
                        modifier = Modifier.padding(
                            vertical = 16.dp,
                            horizontal = 16.dp
                        ),
                        style = TextStyle(
                            fontSize = 22.sp
                        ),
                        fontWeight = FontWeight.Bold
                    )
                    ChipGroup(
                        elements = Currency.values().toList(),
                        onSelectedChanged = {
                            viewModel.loadData(it)
                        },
                        selected = selectedCurrency.value
                    )
                    Divider(modifier = Modifier.height(3.dp))
                }
            }


        }
    ) {
        when (connectionStatus.value) {
            ConnectionStatus.LOADING -> LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
            ConnectionStatus.SUCCESS -> {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(
                        isRefreshing = refreshingState.value == RefreshingState.REFRESHING
                    ),
                    onRefresh = { viewModel.refresh() }) {
                    if (refreshingState.value == RefreshingState.FAILURE)
                        Toasty.error(
                            context,
                            R.string.error_while_loading,
                            Toast.LENGTH_SHORT,
                            true
                        ).show()
                    LazyColumn {
                        items(cryptoCurrencyInfoList.value) {
                            CryptoCurrencyInfoListItem(
                                cryptoCurrencyInfo = it,
                                selectedCurrency = selectedCurrency.value,
                                modifier = Modifier.padding(12.dp)
                            ) {
                                navController.navigate(
                                    Screens.CryptoCurrencyDescriptionScreen.withArgs(
                                        cryptoId = it.id,
                                        cryptoName = it.name,
                                        cryptoImageLink = it.image
                                    )
                                )
                            }
                        }
                    }
                }
            }
            ConnectionStatus.FAILURE -> SomethingWentWrongScreen(
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
    selectedCurrency: Currency,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {

    val decimalFormat by remember { mutableStateOf(DecimalFormat("###,###.##")) }
    val fontFamily by remember {
        mutableStateOf(
            FontFamily(
                Font(R.font.lato_regular),
                Font(R.font.lato_black),
                Font(R.font.lato_black_italic),
                Font(R.font.lato_bold),
                Font(R.font.lato_bold_italic),
                Font(R.font.lato_light),
                Font(R.font.lato_light_italic),
                Font(R.font.lato_italic),
                Font(R.font.lato_thin),
                Font(R.font.lato_thin_italic)
            )
        )
    }

    Surface(modifier = Modifier.clickable { onClick() }) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(cryptoCurrencyInfo.image),
                contentDescription = "crypto image",
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colors.primary,
                        shape = CircleShape
                    )
            )
            Column {
                Row {
                    Text(
                        text = cryptoCurrencyInfo.name,
                        modifier = Modifier.padding(
                            horizontal = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 20.sp
                        ),
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "${selectedCurrency.symbol}${
                            decimalFormat.format(
                                cryptoCurrencyInfo.currentPrice
                            )
                        }",
                        modifier = Modifier.padding(
                            horizontal = 12.dp
                        ),
                        style = TextStyle(
                            fontSize = 20.sp
                        ),
                        fontFamily = fontFamily
                    )

                }
                Row {
                    Text(
                        text = cryptoCurrencyInfo.symbol.uppercase(),
                        modifier = Modifier.padding(
                            horizontal = 12.dp
                        ),
                        color = Color.Gray,
                        fontFamily = fontFamily
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    val sign = sign(cryptoCurrencyInfo.priceChange24h)
                    Text(
                        text = "${
                            when (sign) {
                                1.0 -> "+"
                                else -> ""
                            }
                        }${
                            decimalFormat.format(
                                cryptoCurrencyInfo.priceChange24h
                            )
                        }%",
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = when (sign) {
                            -1.0 -> Color.Red
                            1.0 -> Color.Green
                            else -> Color.Gray
                        },
                        fontFamily = fontFamily
                    )
                }
            }
        }
    }
}