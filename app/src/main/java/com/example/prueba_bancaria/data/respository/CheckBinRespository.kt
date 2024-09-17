package com.example.prueba_bancaria.data.respository

import android.util.Log
import com.example.prueba_bancaria.data.remote.api.ApiBin
import com.example.prueba_bancaria.data.remote.model.BinBody
import com.example.prueba_bancaria.data.remote.model.BinResponse
import com.example.prueba_bancaria.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckBinRespository @Inject constructor(
    private val api: ApiBin,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

) {
    suspend fun checkBin(bin: String): BinResponse? = withContext(ioDispatcher) {
        val response = api.checkBin(bin)
        return@withContext if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }
}