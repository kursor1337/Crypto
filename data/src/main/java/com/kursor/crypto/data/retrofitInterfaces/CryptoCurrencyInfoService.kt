package com.kursor.crypto.data.retrofitInterfaces

import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface CryptoCurrencyInfoService {

    @GET("/coins/markets")
    fun getCryptoCurrencyInfoList(@Body body: RequestBody): Call<List<CryptoCurrencyInfo>>

    fun getCryptoCurrencyInfoList(vs_currency: String) = getCryptoCurrencyInfoList(
        RequestBody(vs_currency)
    )

    data class RequestBody(
        val vs_currency: String
    )

}