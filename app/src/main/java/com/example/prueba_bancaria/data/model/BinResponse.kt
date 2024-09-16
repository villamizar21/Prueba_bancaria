package com.example.prueba_bancaria.data.model

import com.google.gson.annotations.SerializedName

data class BinResponse(
    val success: String,
    val code: Long,
    @SerializedName("BIN")
    val bin: Bin,
    @SerializedName("IP")
    val ip: IpDetails
)
