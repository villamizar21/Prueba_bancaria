package com.example.prueba_bancaria.data.remote.api

import com.example.prueba_bancaria.data.remote.model.BinBody
import com.example.prueba_bancaria.data.remote.model.BinResponse
import com.example.prueba_bancaria.utils.Constans
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiBin {
    @Headers(
        "Content-Type: application/json",
        "x-rapidapi-host: ${Constans.HOST}",
        "x-rapidapi-key: ${Constans.API_KEY}"
    )
    @POST("/")
    suspend fun checkBin(
        @Query("bin") bin: String
    ): Response<BinResponse>
}