package com.example.nepogoda.features.weather

import com.example.nepogoda.features.weather.model.WeatherModel
import com.example.nepogoda.features.weather.usecase.IWeatherUC
import com.example.nepogoda.infrastructure.async.Async
import com.example.nepogoda.infrastructure.async.Uninitialized
import com.example.nepogoda.infrastructure.async.async
import com.example.nepogoda.infrastructure.base.BaseViewModel
import org.koin.android.annotation.KoinViewModel
import org.orbitmvi.orbit.syntax.simple.intent

@KoinViewModel
class WeatherViewModel(
    private val weatherUC: IWeatherUC
) : BaseViewModel<WeatherViewModel.WeatherState, Unit>(WeatherState()) {
    data class WeatherState(
        val weatherState: Async<WeatherModel> = Uninitialized
    )

    fun getWeather() = intent {
        async {
            weatherUC.invoke(city = "Gliwice")
        }.execute(cachedValue = state.weatherState) {
            state.copy(weatherState = it)
        }
    }
}