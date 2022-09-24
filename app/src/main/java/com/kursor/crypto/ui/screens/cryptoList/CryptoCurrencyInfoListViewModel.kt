package com.kursor.crypto.ui.screens.cryptoList

import androidx.lifecycle.ViewModel
import com.kursor.crypto.domain.useCases.LoadCryptoCurrencyInfoListUseCase

class CryptoCurrencyInfoListViewModel(
    val loadCryptoCurrencyInfoListUseCase: LoadCryptoCurrencyInfoListUseCase
) : ViewModel() {

}