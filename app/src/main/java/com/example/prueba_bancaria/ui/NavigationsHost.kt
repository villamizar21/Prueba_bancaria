package com.example.prueba_bancaria.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHots(modifier: Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destinations.MainView.route){
        composable(Destinations.MainView.route) { MainView(navController) }
        composable(Destinations.SecondaryView.route) { PaymentView() }
    }
}