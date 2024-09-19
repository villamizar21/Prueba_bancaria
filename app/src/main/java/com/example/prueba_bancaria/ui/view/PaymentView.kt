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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prueba_bancaria.R
import com.example.prueba_bancaria.ui.components.Button
import com.example.prueba_bancaria.ui.components.ButtonType
import com.example.prueba_bancaria.ui.components.ErrorDialog
import com.example.prueba_bancaria.ui.components.InputCard
import com.example.prueba_bancaria.ui.components.InputValue
import com.example.prueba_bancaria.ui.components.TopAppBar
import com.example.prueba_bancaria.ui.navegation.Destinations
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel
import com.example.prueba_bancaria.utils.Constans


@Composable
fun PaymentView(navController: NavHostController) {
    TopAppBar(
        isMainView = false,
        title = stringResource(id = R.string.compra),
        body = { bodyPayment(navController) },
        clickListener = {
            navController.navigate(Destinations.MainView.route)
        }
    )
}

@Composable
fun bodyPayment(navController: NavHostController) {
    val viewModel: BinCheckViewModel = hiltViewModel()
    val scrollState = rememberScrollState()
    var showDialog by rememberSaveable { mutableStateOf(false) }
    var errorMessage by rememberSaveable { mutableStateOf("") }
    var inputValue by rememberSaveable { mutableStateOf("") }
    var cardValue by rememberSaveable { mutableStateOf("") }
    var isFromColombia by rememberSaveable { mutableStateOf(false) }

    fun validateInputs(): Boolean {
        val isValueValid = inputValue.isNotEmpty() && inputValue.toIntOrNull()
            ?.let { it <= Constans.SALDO } ?: false
        val isCardValid = cardValue.isNotEmpty() && (cardValue.length == 16 || cardValue.length == 15)
        if (!isValueValid) {
            errorMessage = "El valor ingresado no es válido o supera el límite permitido."
        } else if (!isCardValid) {
            errorMessage = "El número de tarjeta es inválido o está incompleto."
        }

        return isValueValid && isCardValid && isFromColombia
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .windowInsetsPadding(WindowInsets.ime)
            .padding(bottom = 16.dp)
    ) {
        TypeOfCard()
        InputValue(value = inputValue, onValueChange = { inputValue = it })
        InputCard(
            value = cardValue,
            onValueChange = { cardValue = it },
            isFromColombia = { isFromColombia = it },
            viewModel = viewModel
        )
        ButtonGoPayment(
            onClick = {
                if (validateInputs()) {
                    updateSaldo(inputValue.toLong())
                    navController.navigate(Destinations.SuccessfulPaymentView.route)
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

fun updateSaldo(value: Long) {
    Constans.SALDO -= value
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