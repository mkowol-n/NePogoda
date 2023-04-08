package com.example.nepogoda.features.weather.usecase

import com.example.nepogoda.features.weather.repository.IWeatherRepository
import org.koin.core.annotation.Factory

@Factory(binds = [IWeatherUC::class])
class WeatherUC(
    private val weatherRepository: IWeatherRepository
) : IWeatherUC {

}