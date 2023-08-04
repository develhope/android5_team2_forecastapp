package co.develhope.meteoapp.features.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherConditions(
    val daily: Daily,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("generationtime_ms") val generationtimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    val elevation: Double,
    @SerializedName("hourly_units") val hourlyHunits: HourlyUnits,
    val hourly: Hourly,
    @SerializedName("current_weather") val currentWeather: CurrentWeather,
)

data class Hourly(
    val time: List<String>,
    @SerializedName("temperature_2m") var temperature: List<Double>,
    @SerializedName("relativehumidity_2m") val relativeHumidity: List<Int>,
    @SerializedName("precipitation_probability") val precipitationProbability: List<Double>,
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

data class CurrentWeather(
    val temperature: Double,
    val windSpeed: Double,
    val weathercode: Int,
    @SerializedName("is_day") val isDay: Int,
    val time: String
)

data class HourlyUnits(
    val time: String,
    @SerializedName("temperature_2m") val temperatureIUnit: String,
    @SerializedName("relativehumidity_2m") val humidityUnit: String,
    val rain: String,
    val cloudcover: String,
    @SerializedName("windspeed_10m") val windSpeedUnit: String,
    @SerializedName("uv_index") val uvIndexUnit: String,
)