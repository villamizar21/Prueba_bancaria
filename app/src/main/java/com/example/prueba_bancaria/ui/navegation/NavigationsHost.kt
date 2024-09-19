package com.example.prueba_bancaria.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prueba_bancaria.ui.view.MainView
import com.example.prueba_bancaria.ui.view.PaymentView
import com.example.prueba_bancaria.ui.view.SuccessfulPayment

@Composable
fun NavigationHots(modifier: Modifier, navController: NavHostController) {

    NavHost(navController = navController, startDestination = Destinations.MainView.route){
        composable(Destinations.MainView.route) { MainView(navController) }
        composable(Destinations.SecondaryView.route) { PaymentView(navController) }
        composable(Destinations.SuccessfulPaymentView.route) { SuccessfulPayment(navController) }
    }
}