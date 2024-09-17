package com.example.prueba_bancaria

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.prueba_bancaria.data.model.BinBody
import com.example.prueba_bancaria.ui.theme.Prueba_bancariaTheme
import com.example.prueba_bancaria.ui.viewModel.BinCheckViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba_bancariaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    BinCheckScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}

@Composable
fun BinCheckScreen(
    viewModel: BinCheckViewModel = hiltViewModel() // Obtén el ViewModel inyectado con Hilt
) {
    var result by remember { mutableStateOf(viewModel.bin) }

    Text(
        text = "Hello lest check bin!"
    )

    // UI para mostrar el resultado del bin check
    Button(onClick = {
        viewModel.checkBin( "528209")// Llama al método del ViewModel
        result = viewModel.bin
    }) {
        Text(text = "Check Bin")
    }

    // Muestra el resultado
    Text(text = result)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Prueba_bancariaTheme {
        BinCheckScreen()
    }
}