package com.kursor.crypto.data.retrofitInterfaces

import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoCurrencyInfoService {

    @GET("/coins/markets")
    fun getCryptoCurrencyInfoList(
        @Query("vs_currencies") vsCurrency: String
    ): Call<List<CryptoCurrencyInfo>>

}