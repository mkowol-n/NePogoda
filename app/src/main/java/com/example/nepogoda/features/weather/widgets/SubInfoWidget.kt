package com.example.nepogoda.features.weather.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SubInfoWidget(modifier: Modifier = Modifier, title: String, subtitle: String, color: Color) {
    Column(
        modifier = modifier.padding(horizontal = 12.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, fontSize = 20.sp, color = color)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = subtitle, fontSize = 20.sp, color = color)
    }
}