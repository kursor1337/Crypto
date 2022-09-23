package com.kursor.crypto.data.repositories

import com.kursor.crypto.data.retrofitInterfaces.CryptoCurrencyDescriptionService
import com.kursor.crypto.domain.ConnectionException
import com.kursor.crypto.domain.repositories.CryptoCurrencyDescriptionRepository
import com.kursor.crypto.model.entities.CryptoCurrencyDescription
import retrofit2.Retrofit

class CryptoCurrencyDescriptionRepositoryImpl(
    private val retrofit: Retrofit
) : CryptoCurrencyDescriptionRepository {

    private val service: CryptoCurrencyDescriptionService =
        retrofit.create(CryptoCurrencyDescriptionService::class.java)

    override suspend fun getCryptoCurrencyDescription(id: String): CryptoCurrencyDescription {
        return service.getCryptoCurrencyDescription(id).execute().body()
            ?: throw ConnectionException(retrofit.baseUrl().toString())
    }
}