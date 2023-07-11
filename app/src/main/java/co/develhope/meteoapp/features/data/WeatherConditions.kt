package co.develhope.meteoapp.features.data

import com.google.gson.annotations.SerializedName


data class WeatherConditions(
    val hourly: Hourly,
    val daily: Daily
)

data class Hourly(
    val time: List<String>,
    @SerializedName("temperature_2m") var temperature: List<Double>,
    @SerializedName("relativehumidity_2m") val relativeHumidity: List<Int>,
    @SerializedName("dewpoint_2m") val dewPoint: List<Double>,
    @SerializedName("apparent_temperature") val apparentTemperature: List<Double>,
    val rain: List<Double>,
    val weathercode: List<Int>,
    val cloudcover: List<Int>,
    @SerializedName("windspeed_10m") val windspeed10m: List<Double>,
    @SerializedName("uv_index") val uvIndex: List<Double>,
    @SerializedName("is_day") val isDay: List<Int>,
    @SerializedName("winddirection_10m") val windDirection: List<Int>
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