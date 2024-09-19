package com.example.prueba_bancaria.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import com.example.prueba_bancaria.utils.Constans
import com.example.prueba_bancaria.utils.PrefixTransformation

@Composable
fun InputValue(value: String, onValueChange: (String) -> Unit) {
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