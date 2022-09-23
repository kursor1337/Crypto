package com.kursor.crypto.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class CryptoCurrencyInfo(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val priceChange24h: Double
)
