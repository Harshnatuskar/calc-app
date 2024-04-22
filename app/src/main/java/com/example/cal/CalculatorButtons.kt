package com.example.cal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.cal.ui.theme.teko

@Composable
fun CalculatorButton(
    symbol:String,
    modifier: Modifier,
    onClick: () -> Unit,
    fontFamily: androidx.compose.ui.text.font.FontFamily = teko) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
        .clip(CircleShape)
        .clickable { onClick() }
        .fillMaxWidth(),
    ){
        Text(
            text = symbol,
            textAlign = TextAlign.Center,
            fontFamily = fontFamily,
            fontSize = 38.sp,
            color = Color.White
        )
    }

}