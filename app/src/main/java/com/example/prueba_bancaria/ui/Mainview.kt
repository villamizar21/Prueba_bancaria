package com.example.prueba_bancaria.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.prueba_bancaria.ui.components.Footer
import com.example.prueba_bancaria.ui.components.TopAppBar
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel

@Composable
fun MainView(navController: NavHostController) {
    TopAppBar(isMainView = true, title = "Hola, ANDERSON", body = { body() })
    Footer(navController)
}

@Composable
fun body(){
    Column(modifier = Modifier.fillMaxSize()){
        novedades()
        Spacer(modifier = Modifier.height(12.dp))
        tarjetas()
    }
}

@Composable
fun tarjetas() {
    Column {
        Text(text = "Mis productos",modifier = Modifier
            .padding(horizontal = 16.dp),fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation( // Elevación de la tarjeta
                defaultElevation = 8.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE20613)
            )

        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Mis Cuentas",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Saldo disponible",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
                Text(
                    text = "$452.594",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Saldo total: $452.594",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun novedades(){
    Column {
        Text(text = "Novedades",modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp),fontSize = 18.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "¿Necesita ayuda? Descuide, escríbanos a través del chat...",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}