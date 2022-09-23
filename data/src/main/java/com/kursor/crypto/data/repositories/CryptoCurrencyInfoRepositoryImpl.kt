package com.kursor.crypto.data.repositories

import com.kursor.crypto.data.retrofitInterfaces.CryptoCurrencyInfoService
import com.kursor.crypto.domain.ConnectionException
import com.kursor.crypto.domain.repositories.CryptoCurrencyInfoRepository
import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import retrofit2.Retrofit

class CryptoCurrencyInfoRepositoryImpl(
    private val retrofit: Retrofit
) : CryptoCurrencyInfoRepository {

    private val service: CryptoCurrencyInfoService =
        retrofit.create(CryptoCurrencyInfoService::class.java)

    override suspend fun getCryptoCurrencyInfoList(vsCurrency: String): List<CryptoCurrencyInfo> {
        return service.getCryptoCurrencyInfoList(vsCurrency).execute().body()
            ?: throw ConnectionException(retrofit.baseUrl().toString())
    }

}