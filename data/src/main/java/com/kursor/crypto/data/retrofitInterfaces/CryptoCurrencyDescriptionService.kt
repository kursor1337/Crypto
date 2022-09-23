package com.kursor.crypto.data.retrofitInterfaces

import com.kursor.crypto.model.entities.CryptoCurrencyDescription
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoCurrencyDescriptionService {

    @GET("/coins/{id}")
    fun getCryptoCurrencyDescription(@Path("id") id: String, body: RequestBody = RequestBody(id)): Call<CryptoCurrencyDescription>


    data class RequestBody(
        val id: String,
        val localization: Boolean = false
    )

}