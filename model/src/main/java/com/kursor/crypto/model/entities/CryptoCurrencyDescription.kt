package com.kursor.crypto.model.entities

import kotlinx.serialization.Serializable

@Serializable
data class CryptoCurrencyDescription(
    val id: String,
    val symbol: String,
    val description: String,
    val categories: List<String>
)
