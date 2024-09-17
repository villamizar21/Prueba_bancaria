package com.example.prueba_bancaria.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba_bancaria.R
import com.example.prueba_bancaria.ui.components.TopAppBar
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel
import com.example.prueba_bancaria.utils.PrefixTransformation
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


@Composable
fun PaymentView() {
    TopAppBar(
        isMainView = false,
        title = stringResource(id = R.string.pago_de_cartera),
        body = { bodyPayment() })
}

@Composable
fun bodyPayment() {
    Column(Modifier.fillMaxSize()) {
       
        InputValue()
        InputCard()
    }
}



@Composable
fun InputValue() {
    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    val textFieldColors = TextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedIndicatorColor = if (isError) Color.Red else Color.Gray,
        unfocusedIndicatorColor = if (isError) Color.Red else Color.Gray
    )

    LaunchedEffect(text) {
        isError = text.toIntOrNull()?.let { it > 400 } ?: false
    }
    Column {
        Text(
            text = stringResource(id = R.string.valor_a_comprar),
            modifier = Modifier.padding(8.dp),
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = { newText ->
                text = newText
                isError = text.toIntOrNull()?.let { it > 400 } ?: false
            },

            colors = textFieldColors,
            supportingText = {
                if (isError) {
                    Text(
                        text = "El valor ingresado supera el límite permitido de $400.",
                        color = Color.Red
                    )
                }
            },
            visualTransformation = PrefixTransformation("$")
        )
    }
}

@Composable
fun InputCard(
    viewModel: BinCheckViewModel = hiltViewModel()
) {
    var text by rememberSaveable { mutableStateOf("") }
    var isError by rememberSaveable { mutableStateOf(false) }
    fun updateText(newText: String) {
        if (newText.length <= 16) {
            text = newText
            isError = true
        }
        if (newText.isEmpty()) {
            isError = false
        }

    }
    LaunchedEffect(text) {
        if (text.length == 16) {
            viewModel.checkBin(text)
            isError = false
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
            value = text,
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
                        text = "Bin invalido",
                        color = Color.Red
                    )
                }
            },
            placeholder = {
                Text("0000-0000-0000-0000")
            },
        )
    }

    viewModel.binResponse?.let {

    }
}

