package com.example.prueba_bancaria.data.model

import com.google.gson.annotations.SerializedName

data class Bin(
    val valid: Boolean,
    val number: Int,
    val length: Int,
    val scheme: String,
    val brand: String,
    val type: String,
    val level: String,
    @SerializedName("is_commercial")
    val isCommercial: String,
    @SerializedName("is_prepaid")
    val isPprepaid: String,
    val currency: String,
    val issuer: Issuer,
    val country: Country
)
data class IpDetails(
    val code: Int,
    @SerializedName("IP")
    val ip: String,
    val result: String,
    val message: String
)
