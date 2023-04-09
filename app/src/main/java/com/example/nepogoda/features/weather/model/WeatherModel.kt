package com.example.nepogoda.features.weather.model

import java.time.LocalDate
import java.util.*

data class WeatherModel(
    val city: String,
    val date: LocalDate,
    val desc: String,
    val temp: String,
    val wind: String,
    val visibility: String,
    val humidity: String,
    val backgroundColor: WeatherBackground = randColor()
)

private fun randColor(): WeatherBackground {
    return WeatherBackground.values()[Random().nextInt(WeatherBackground.values().size)]
}