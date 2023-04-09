package com.example.nepogoda.features.weather.usecase

import com.example.nepogoda.features.weather.model.WeatherModel

interface IWeatherUC {
    suspend fun invoke(city: String): WeatherModel
}