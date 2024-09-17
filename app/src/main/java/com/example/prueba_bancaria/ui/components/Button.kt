package com.example.prueba_bancaria.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prueba_bancaria.ui.theme.Disabled
import com.example.prueba_bancaria.ui.theme.Grey
import com.example.prueba_bancaria.ui.theme.Red

enum class ButtonType {
    ACCEPT, CANCEL
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    text: String,
    type: ButtonType,
    isEnabled: Boolean = true,
    clickListener: () -> Unit = {}
) {
    val shape = RoundedCornerShape(8.dp)
    val (backgroundColor, borderStroke, textColor) = when (type) {
        ButtonType.ACCEPT -> Triple(
            Red,
            BorderStroke(width = 0.dp, color = Color.Transparent),
            Color.White
        )

        ButtonType.CANCEL -> Triple(
            Grey,
            BorderStroke(width = 1.dp, color = Red),
            Color.Black
        )
    }
    Surface(
        color = if (isEnabled) backgroundColor else Disabled,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .border(borderStroke)
            .clip(shape = shape)
            .clickable(enabled = isEnabled) { clickListener() }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = if (isEnabled) textColor else Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}