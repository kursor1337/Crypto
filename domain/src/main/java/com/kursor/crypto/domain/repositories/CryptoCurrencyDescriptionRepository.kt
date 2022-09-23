package com.kursor.crypto.domain.repositories

import com.kursor.crypto.model.entities.CryptoCurrencyDescription

interface CryptoCurrencyDescriptionRepository {

    suspend fun getCryptoCurrencyDescription(id: String): CryptoCurrencyDescription

}