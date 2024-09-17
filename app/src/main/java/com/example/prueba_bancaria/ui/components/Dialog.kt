package com.example.prueba_bancaria.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Error") },
        text = { Text(text = "No es posible realizar la compra.") },
        confirmButton = {
            androidx.compose.material3.Button(
                onClick = { onDismiss() }
            ) {
                Text("OK")
            }
        }
    )
}