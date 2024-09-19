package com.example.prueba_bancaria.data.respository

import android.util.Log
import com.example.prueba_bancaria.data.remote.api.ApiBin
import com.example.prueba_bancaria.data.remote.model.BinBody
import com.example.prueba_bancaria.data.remote.model.BinResponse
import com.example.prueba_bancaria.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class CheckBinRespository @Inject constructor(
    private val api: ApiBin,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) {
    suspend fun checkBin(bin: String): Result<BinResponse> = withContext(ioDispatcher) {
        try {
            val response = api.checkBin(bin)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(NullPointerException("Response body is null"))
            } else {
                Result.failure(HttpException(response))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}