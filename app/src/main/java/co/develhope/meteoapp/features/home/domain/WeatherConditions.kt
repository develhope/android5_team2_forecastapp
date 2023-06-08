package co.develhope.meteoapp.features.home.domain

import com.google.gson.annotations.SerializedName


data class WeatherConditions(
    val hourly: Hourly,
    val daily: Daily
)

data class Hourly(
    val time: List<String>,
    @SerializedName("temperature_2m") val temperature: List<Double>,
    val rain: Any
)

data class Daily(
    val weathercode: List<Int>,
    val time: List<String>,
    @SerializedName("temperature_2m_max") val maxTemperature: List<Double>,
    @SerializedName("temperature_2m_min") val minTemperature: List<Double>,
    @SerializedName("rain_sum") val rainSum: List<Double>,
    @SerializedName("windspeed_10m_max") val windMax: List<Double>,
    val dayWeek: List<String>
)