package com.example.nepogoda.features.weather.repository

import org.koin.core.annotation.Single

@Single(binds = [IWeatherRepository::class])
class WeatherRepository: IWeatherRepository {
}