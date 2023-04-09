package com.example.nepogoda.infrastructure.sources.remote

import com.example.nepogoda.infrastructure.dto.WeatherDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRemoteSource {

    @GET("weather/")
    suspend fun getWeather(@Query("q") cityName: String): WeatherDTO
}