package com.example.nepogoda.infrastructure.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherDTO(
    val coord: CoordDTO,
    val weather: List<WeatherDataDTO>,
    val base: String,
    val main: MainDataDTO,
    val visibility: Int,
    val wind: WindDataDTO,
    val clouds: CloudsDataDTO,
    val dt: Long,
    val sys: SysDataDTO,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
): Parcelable

@Parcelize
data class CoordDTO(
    val lon: Double,
    val lat: Double
): Parcelable

@Parcelize
data class WeatherDataDTO(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
): Parcelable

@Parcelize
data class MainDataDTO(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int
): Parcelable

@Parcelize
data class WindDataDTO(
    val speed: Double,
    val deg: Int,
    val gust: Double
): Parcelable

@Parcelize
data class CloudsDataDTO(
    val all: Int
): Parcelable

@Parcelize
data class SysDataDTO(
    val type: Int,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
): Parcelable