package com.kursor.crypto.model.entities

data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val currentPrice: Double,
    val priceChange24h: Double
)
