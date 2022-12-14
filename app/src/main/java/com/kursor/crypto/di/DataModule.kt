package com.kursor.crypto.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.kursor.crypto.data.repositories.CryptoCurrencyDescriptionRepositoryImpl
import com.kursor.crypto.data.repositories.CryptoCurrencyInfoRepositoryImpl
import com.kursor.crypto.domain.repositories.CryptoCurrencyDescriptionRepository
import com.kursor.crypto.domain.repositories.CryptoCurrencyInfoRepository
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
val dataModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://api.coingecko.com/api/v3/")
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            ).build()
    }

    single<CryptoCurrencyDescriptionRepository> {
        CryptoCurrencyDescriptionRepositoryImpl(retrofit = get())
    }

    single<CryptoCurrencyInfoRepository> {
        CryptoCurrencyInfoRepositoryImpl(retrofit = get())
    }

}