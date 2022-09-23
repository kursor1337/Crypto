package com.kursor.crypto.model.entities

data class CryptoCurrencyDescription(
    val id: String,
    val symbol: String,
    val description: String,
    val categories: List<String>
)
