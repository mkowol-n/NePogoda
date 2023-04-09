package com.example.nepogoda.features.weather.repository

import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.infrastructure.mapper.mapToDomain
import com.example.nepogoda.infrastructure.sources.remote.WeatherRemoteSource
import org.koin.core.annotation.Single

@Single(binds = [IWeatherRepository::class])
class WeatherRepository(private val weatherRemoteSource: WeatherRemoteSource) : IWeatherRepository {
    override suspend fun getWeatherForCity(city: String): WeatherModel {
        return weatherRemoteSource.getWeather(city).mapToDomain()
    }
}