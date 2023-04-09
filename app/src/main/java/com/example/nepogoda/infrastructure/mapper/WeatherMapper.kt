package com.example.nepogoda.infrastructure.mapper

import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.infrastructure.dto.WeatherDTO

fun WeatherDTO.mapToDomain(): WeatherModel {
    return WeatherModel(
        city = this.name
    )
}