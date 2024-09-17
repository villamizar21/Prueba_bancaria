package com.example.prueba_bancaria.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_bancaria.data.remote.model.BinResponse
import com.example.prueba_bancaria.data.respository.CheckBinRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinCheckViewModel @Inject constructor(
    private val repository: CheckBinRespository
) : ViewModel() {

     var binResponse by mutableStateOf<BinResponse?>(null)
        private set

   // viewModelScope.launch {
     fun checkBin(binBody: String){
        viewModelScope.launch {
         binResponse = repository.checkBin(binBody)
        }
    }
}