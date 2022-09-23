package com.kursor.crypto.domain.repositories

import com.kursor.crypto.model.entities.CryptoCurrencyInfo

interface CryptoCurrencyInfoRepository {

    suspend fun getCryptoCurrencyInfoList(vsCurrency: String): List<CryptoCurrencyInfo>

}