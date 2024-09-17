package com.example.prueba_bancaria.ui.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba_bancaria.data.respository.CheckBinRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinCheckViewModel @Inject constructor(
    private val repository: CheckBinRespository
) : ViewModel() {

     var bin = "Result not available yet"

   // viewModelScope.launch {
     fun checkBin(binBody: String){
        viewModelScope.launch {
         bin = repository.checkBin(binBody).toString()
            Log.e("", bin.toString())
        }
    }
}