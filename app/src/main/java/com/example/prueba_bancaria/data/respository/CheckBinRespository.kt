package com.example.prueba_bancaria.data.respository

import android.util.Log
import com.example.prueba_bancaria.data.api.ApiBin
import com.example.prueba_bancaria.data.model.BinBody
import com.example.prueba_bancaria.data.model.BinResponse
import com.example.prueba_bancaria.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CheckBinRespository @Inject constructor(
    private val api: ApiBin,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher

){
    suspend fun checkBin(bin: String): Unit = withContext(ioDispatcher){
        api.checkBin(bin).run {
            if(isSuccessful){
                Log.e("","${body()}")
            }
        }
    }
}