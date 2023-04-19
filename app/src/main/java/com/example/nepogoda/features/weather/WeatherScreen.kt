package com.example.nepogoda.features.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nepogoda.coreUI.FitWindow
import com.example.nepogoda.features.weather.widgets.SubInfoWidget
import com.example.nepogoda.infrastructure.async.FullscreenAsyncHandler
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun WeatherScreen(viewModel: WeatherViewModel = koinViewModel()) {
    val state by viewModel.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getWeather()
    }
    FullscreenAsyncHandler(state = state.weatherState, onRetryAction = { viewModel.getWeather() }) {
        FitWindow(
            modifier = Modifier
                .fillMaxSize()
                .background(it.backgroundColor.color)
        ){
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = it.city, fontWeight = FontWeight.Bold, fontSize = 32.sp)
                Spacer(modifier = Modifier.height(32.dp))
                Box(modifier = Modifier
                    .background(color = Color.Black, shape = RoundedCornerShape(30.dp))
                    .padding(vertical = 8.dp, horizontal = 16.dp)) {
                    Text(text = "${it.date.dayOfWeek.name}, ${it.date.dayOfMonth} ${it.date.month.name}", color = it.backgroundColor.color, fontSize = 28.sp)
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = it.desc, fontSize = 24.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = "${it.temp}°", fontSize = 120.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(). background(color = Color.Black, shape = RoundedCornerShape(15.dp))) {
                    SubInfoWidget(title = it.wind, subtitle = "Wind", modifier = Modifier.weight(1f), color = it.backgroundColor.color)
                    SubInfoWidget(title = it.humidity, subtitle = "Humidity", modifier = Modifier.weight(1f), color = it.backgroundColor.color)
                    SubInfoWidget(title = it.visibility, subtitle = "Visibility", modifier = Modifier.weight(1f), color = it.backgroundColor.color)
                }
            }
        }
    }
}