package com.example.prueba_bancaria.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.prueba_bancaria.R
import com.example.prueba_bancaria.ui.components.Button
import com.example.prueba_bancaria.ui.components.ButtonType
import com.example.prueba_bancaria.ui.components.TopAppBar
import com.example.prueba_bancaria.ui.navegation.Destinations

@Composable
fun SuccessfulPayment(navController: NavHostController) {
    TopAppBar(isMainView = false,
        title = stringResource(id = R.string.compra),
        body = { BodySuccessful(navController) }
    )
}

@Composable
fun BodySuccessful(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.exito),
            contentDescription = "pago-exitoso",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.pago_exitoso),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.padding(horizontal = 36.dp),
            text = stringResource(id = R.string.salir),
            type = ButtonType.ACCEPT,
            clickListener = {
                navController.navigate(Destinations.MainView.route)
            }
        )
    }
}

