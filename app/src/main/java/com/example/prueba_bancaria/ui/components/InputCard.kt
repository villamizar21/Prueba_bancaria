package com.example.prueba_bancaria.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_bancaria.R
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel
import com.example.prueba_bancaria.utils.Constans


@Composable
fun InputCard(
    value: String, onValueChange: (String) -> Unit,
    isFromColombia: (Boolean) -> Unit,
    viewModel: BinCheckViewModel

) {
    var isError by rememberSaveable { mutableStateOf(false) }

    fun updateText(newText: String) {
        if (newText.length <= 16) {
            onValueChange(newText)
            isError = newText.length !in 15..16
        }
        if (newText.isEmpty()) {
            isError = false
            Constans.TyPECARD = ""
        }
    }

    LaunchedEffect(value) {
        if (value.length == 6) {
            viewModel.checkBin(value)
        }
        if (value.isEmpty()) {
            isError = false
            Constans.TyPECARD = ""
        }
    }

    val binResponse = viewModel.binResponse
    LaunchedEffect(binResponse) {
        viewModel.binResponse?.let {
            Constans.TyPECARD = it.bin.issuer.name
            Log.e("Issuer", it.bin.issuer.name)
            Log.e("Country", it.bin.country.name)
            if (it.bin.country.name != "COLOMBIA") {
                isError = true
            } else {
                isError = false
                isFromColombia(true)
            }
        }
    }
    Column {
        Text(
            text = stringResource(id = R.string.numero_tarjeta),
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = { newText ->
                updateText(newText)
            },
            supportingText = {
                if (isError) {
                    Text(
                        text = "Bin inválido",
                        color = Color.Red
                    )
                }
            },
            placeholder = {
                Text("0000-0000-0000-0000")
            },
        )
    }

}
