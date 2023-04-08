package com.example.nepogoda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.nepogoda.features.weather.WeatherScreen
import com.example.nepogoda.ui.theme.NePogodaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NePogodaTheme {
                WeatherScreen()
            }
        }
    }
}