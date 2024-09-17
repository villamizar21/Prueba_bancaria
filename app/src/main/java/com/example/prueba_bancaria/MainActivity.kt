package com.example.prueba_bancaria

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.prueba_bancaria.ui.NavigationHots
import com.example.prueba_bancaria.ui.theme.Prueba_bancariaTheme
import com.example.prueba_bancaria.utils.HideSystemUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Prueba_bancariaTheme {
                HideSystemUI().hideSystemUI(this.window)
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {
                        innerPadding ->
                    NavigationHots(Modifier.padding(innerPadding),navController)
                }
            }
        }
    }
}

