package com.example.prueba_bancaria.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba_bancaria.R
import com.example.prueba_bancaria.ui.components.Button
import com.example.prueba_bancaria.ui.components.ButtonType
import com.example.prueba_bancaria.ui.components.ErrorDialog
import com.example.prueba_bancaria.ui.components.TopAppBar
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel
import com.example.prueba_bancaria.utils.Constans
import com.example.prueba_bancaria.utils.PrefixTransformation



@Composable
fun PaymentView() {
    TopAppBar(
        isMainView = false,
        title = stringResource(id = R.string.pago_de_cartera),
        body = { bodyPayment() }
    )
}

@Composable
fun bodyPayment() {
    val scrollState = rememberScrollState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var cardValue by rememberSaveable { mutableStateOf("") }

    fun validateInputs(): Boolean {
        val isValueValid = inputValue.isNotEmpty() && inputValue.toIntOrNull()?.let { it <= Constans.SALDO } ?: false
        val isCardValid = cardValue.isNotEmpty() && cardValue.length == 16

        if (!isValueValid) {
            errorMessage = "El valor ingresado no es válido o supera el límite permitido."
        } else if (!isCardValid) {
            errorMessage = "El número de tarjeta es inválido o está incompleto."
        }

        return isValueValid && isCardValid
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .windowInsetsPadding(WindowInsets.ime)
    ) {
        TypeOfCard()
        InputValue(value = inputValue, onValueChange = { inputValue = it })
        InputCard(value = cardValue, onValueChange = { cardValue = it })
        ButtonGoPayment(
            onClick = {
                if (validateInputs()) {
                    // Proceed with payment
                } else {
                    showDialog = true
                }
            }
        )
    }

    if (showDialog) {
        ErrorDialog(onDismiss = { showDialog = false })
    }
}

@Composable
fun TypeOfCard() {
    if (Constans.TyPECARD.isNotEmpty()) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = Constans.TyPECARD,
                fontSize = 14.sp, color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.Black)
            )
        }
    }
}

@Composable
fun InputValue(value: String, onValueChange: (String) -> Unit) {
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

    LaunchedEffect(value) {
        isError = value.toIntOrNull()?.let { it > Constans.SALDO } ?: false
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
            value = value,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            onValueChange = { newText ->
                onValueChange(newText)
                isError = value.toIntOrNull()?.let { it > Constans.SALDO } ?: false
            },
            colors = textFieldColors,
            supportingText = {
                if (isError) {
                    Text(
                        text = "El valor ingresado supera el límite permitido de $${Constans.SALDO}.",
                        color = Color.Red
                    )
                }
            },
            visualTransformation = PrefixTransformation("$"),
        )
    }
}

@Composable
fun InputCard(value: String, onValueChange: (String) -> Unit,
              viewModel: BinCheckViewModel = hiltViewModel(),

              ) {
    var isError by rememberSaveable { mutableStateOf(false) }

    fun updateText(newText: String) {
        if (newText.length <= 16) {
            onValueChange(newText)
            isError = true
        }
        if (newText.isEmpty()) {
            isError = false
        }
    }

    LaunchedEffect(value) {
        if (value.length == 16) {
            viewModel.checkBin(value)
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
        if (it.bin.issuer.name.isNotEmpty())
            Constans.TyPECARD = it.bin.issuer.name
    }
}

@Composable
fun ButtonGoPayment(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            modifier = Modifier
                .padding(horizontal = 36.dp),
            text = stringResource(id = R.string.next),
            type = ButtonType.ACCEPT,
            isEnabled = Constans.TyPECARD.isNotEmpty(),
            clickListener = onClick
        )
    }
}
