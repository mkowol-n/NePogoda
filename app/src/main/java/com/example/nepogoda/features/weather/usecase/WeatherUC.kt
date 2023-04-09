package com.example.nepogoda.features.weather.usecase

import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.features.weather.repository.IWeatherRepository
import org.koin.core.annotation.Factory

@Factory(binds = [IWeatherUC::class])
class WeatherUC(
    private val weatherRepository: IWeatherRepository
) : IWeatherUC {
    override suspend fun invoke(city: String): WeatherModel {
        return weatherRepository.getWeatherForCity(city)
    }

}