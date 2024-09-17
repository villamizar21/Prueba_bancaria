package com.example.prueba_bancaria.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.prueba_bancaria.ui.Destinations

@Composable
fun Footer(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        FooterWithButtons(
            modifier = Modifier.align(Alignment.BottomCenter),
            selectedIndex = selectedIndex.value,
            onItemSelected = { index ->
                selectedIndex.value = index
                when(index){
                    4 -> navController.navigate(Destinations.SecondaryView.route)
                }
            }
        )
    }
}

@Composable
fun FooterWithButtons(
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    Surface(
        color = Color.White,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FooterButton(
                icon = Icons.Default.Home,
                label = "Inicio",
                onClick = { onItemSelected(0) },
                ishome = true
            )
            FooterButton(
                icon = Icons.Default.Search,
                label = "Buscar",
                onClick = { onItemSelected(1) },
                ishome = false
            )
            FooterButton(
                icon = Icons.Default.Favorite,
                label = "Favoritos",
                onClick = { onItemSelected(2) },
                ishome = false
            )

            FooterButton(
                icon = Icons.Default.ShoppingCart,
                label = "Pago de cartera",
                onClick = { onItemSelected(4) },
                ishome = false
            )
        }
    }
}

@Composable
fun FooterButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    ishome:Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if(ishome) Color.Red else Color.Gray
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

