package com.example.prueba_bancaria.utils

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

object Constans {

    const val BASE_URL = "https://bin-ip-checker.p.rapidapi.com/"
    const val HOST = "bin-ip-checker.p.rapidapi.com"
    const val API_KEY = "e0d8f5084cmsh2d3bc68c3448ad1p16d43cjsn16d89e00def8"
   // const val SALDO = 452594
    var TyPECARD  by mutableStateOf("")
    var SALDO  by mutableLongStateOf(452594)
}

fun formatToPeso(value: Long): String {
    val format = NumberFormat.getCurrencyInstance(Locale("es", "CO"))
    return format.format(value).replace(",00", "") // Formatear sin decimales si no son necesarios
}

class PrefixTransformation(val prefix: String) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return PrefixFilter(text, prefix)
    }
}

fun PrefixFilter(number: AnnotatedString, prefix: String): TransformedText {

    var out = prefix + number.text
    val prefixOffset = prefix.length

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return offset + prefixOffset
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset < prefixOffset) return 0
            return offset - prefixOffset
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}