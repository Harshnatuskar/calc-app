package com.example.cal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol:String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Box(modifier = Modifier
        .clip(CircleShape)
        .clickable { onClick() }
        .fillMaxWidth(),
    ){
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White
        )
    }

}