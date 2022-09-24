package com.kursor.crypto.domain.useCases

import com.kursor.crypto.domain.repositories.CryptoCurrencyDescriptionRepository
import com.kursor.crypto.domain.tryRequest
import com.kursor.crypto.model.entities.CryptoCurrencyDescription

class LoadCryptoCurrencyDescriptionUseCase(
    private val cryptoCurrencyDescriptionRepository: CryptoCurrencyDescriptionRepository
) {

    suspend operator fun invoke(id: String): Result<CryptoCurrencyDescription> = tryRequest {
        cryptoCurrencyDescriptionRepository.getCryptoCurrencyDescription(id)
    }

}