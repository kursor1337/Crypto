package com.kursor.crypto.data.retrofitInterfaces

import com.kursor.crypto.model.entities.CryptoCurrencyDescription
import kotlinx.serialization.Serializable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoCurrencyDescriptionService {

    @GET("coins/{id}")
    fun getCryptoCurrencyDescription(
        @Path("id") id: String,
        @Query("localization") localization: Boolean = false
    ): Call<CryptoCurrencyDescription>

}