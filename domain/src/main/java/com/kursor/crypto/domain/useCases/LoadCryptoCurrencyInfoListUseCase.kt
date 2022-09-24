package com.kursor.crypto.domain.useCases

import com.kursor.crypto.domain.repositories.CryptoCurrencyInfoRepository
import com.kursor.crypto.domain.tryRequest

class LoadCryptoCurrencyInfoListUseCase(
    private val cryptoCurrencyInfoRepository: CryptoCurrencyInfoRepository
) {

    suspend operator fun invoke(vsCurrency: String) = tryRequest {
        cryptoCurrencyInfoRepository.getCryptoCurrencyInfoList(vsCurrency)
    }

}