package com.example.prueba_bancaria.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Error") },
        text = { Text(text = "No es posible realizar la compra.") },
        containerColor = Color.White,
        confirmButton = {
            androidx.compose.material3.Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("OK")
            }
        }
    )
}