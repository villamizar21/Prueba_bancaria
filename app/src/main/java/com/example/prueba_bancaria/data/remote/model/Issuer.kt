package com.example.prueba_bancaria.data.remote.model

import com.google.gson.annotations.SerializedName

data class Issuer(
    val name: String,
    val website: String,
    val phone: String,
)
data class Country(
    val name: String,
    val native: String,
    val flag: String,
    val numeric: String,
    val capital: String,
    val currency: String,
    @SerializedName("currency_name")
    val currencyName: String,
    @SerializedName("currency_symbol")
    val currencySymbol: String,
    val region: String,
    val subregion: String,
    val idd: String,
    val alpha2: String,
    val alpha3: String,
    val language: String,
    @SerializedName("")
    val languageCode: String
)