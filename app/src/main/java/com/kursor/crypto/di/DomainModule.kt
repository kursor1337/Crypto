package com.kursor.crypto.di

import com.kursor.crypto.domain.useCases.LoadCryptoCurrencyDescriptionUseCase
import com.kursor.crypto.domain.useCases.LoadCryptoCurrencyInfoListUseCase
import org.koin.dsl.module

val domainModule = module {

    factory {
        LoadCryptoCurrencyDescriptionUseCase(
            cryptoCurrencyDescriptionRepository = get()
        )
    }

    factory {
        LoadCryptoCurrencyInfoListUseCase(
            cryptoCurrencyInfoRepository = get()
        )
    }

}