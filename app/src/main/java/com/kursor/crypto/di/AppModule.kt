package com.kursor.crypto.di

import com.kursor.crypto.model.entities.CryptoCurrencyDescription
import com.kursor.crypto.ui.screens.cryptoDescription.CryptoCurrencyDescriptionViewModel
import com.kursor.crypto.ui.screens.cryptoList.CryptoCurrencyInfoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        CryptoCurrencyInfoListViewModel(
            loadCryptoCurrencyInfoListUseCase = get()
        )
    }

    viewModel {
        CryptoCurrencyDescriptionViewModel(
            loadCryptoCurrencyDescriptionUseCase = get()
        )
    }

}