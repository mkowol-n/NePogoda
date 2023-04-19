package com.example.nepogoda.features.weather.repository

import com.example.nepogoda.features.weather.model.WeatherModel

interface IWeatherRepository {
    suspend fun getWeatherForCity(city: String): WeatherModel
}