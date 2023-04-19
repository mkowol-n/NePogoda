package com.example.nepogoda.infrastructure.mapper

import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.infrastructure.dto.WeatherDTO
import java.time.LocalDate
import kotlin.math.round

fun WeatherDTO.mapToDomain(): WeatherModel {
    return WeatherModel(
        city = this.name,
        date = LocalDate.now(),
        desc = this.weather.map { "${it.description}, " }.toString().replace("[", "")
            .replace("]", "").removeSuffix (", "),
        temp = round((this.main.temp - 272.15)) .toString(),
        humidity = "${this.main.humidity}%",
        wind = "${this.wind.speed}km/h",
        visibility = "${(this.visibility / 1000)}km",
    )
}