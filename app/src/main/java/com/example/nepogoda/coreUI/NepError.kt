package com.example.nepogoda.coreUI

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NepError(onRetryAction: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Coś poszło nie tak")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onRetryAction) {
            Text(text = "Ponów")
        }
    }
}