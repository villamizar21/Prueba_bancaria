package com.example.prueba_bancaria.data.api

import com.example.prueba_bancaria.data.model.BinBody
import com.example.prueba_bancaria.data.model.BinResponse
import com.example.prueba_bancaria.utils.Constans
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiBin {
    @Headers(
        "Content-Type: application/json",
        "x-rapidapi-host: ${Constans.HOST}",
        "x-rapidapi-key: ${Constans.API_KEY}"
    )
    @POST("/")
    suspend fun checkBin(
        @Body binRequest: BinBody
    ): BinResponse
}