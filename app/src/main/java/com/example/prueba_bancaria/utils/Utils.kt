package com.example.prueba_bancaria.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object Constans {

    const val BASE_URL = "https://bin-ip-checker.p.rapidapi.com/"
    const val HOST = "bin-ip-checker.p.rapidapi.com"
    const val API_KEY = "e0d8f5084cmsh2d3bc68c3448ad1p16d43cjsn16d89e00def8"
    const val SALDO = 452594
}

object Utilities {
    fun formatWithDecimalFormat(numberString: String): String {
        if (numberString.isEmpty()) return ""
        try {
            val symbols = DecimalFormatSymbols(Locale.GERMANY)
            val df = DecimalFormat("#,######", symbols)
            return df.format(numberString.toLong())
        } catch (e: NumberFormatException) {
            return numberString
        }
    }

    fun formatText(input: String): String {
        val rawNumber = input.replace("[^\\d]".toRegex(), "")
        val formattedNumber = if (rawNumber.isNotEmpty()) {
            val number = rawNumber.toInt()
            val formatter = DecimalFormat("#,###")
            formatter.format(number)
        } else ""
        return "$formattedNumber"
    }
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