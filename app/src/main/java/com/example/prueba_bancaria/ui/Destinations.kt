package com.example.prueba_bancaria.ui

sealed class Destinations(
    val route: String
    ){
    object MainView: Destinations("patanlla1")
    object SecondaryView: Destinations("patanlla2")
}