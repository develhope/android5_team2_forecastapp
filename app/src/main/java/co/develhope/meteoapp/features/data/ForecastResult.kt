package co.develhope.meteoapp.features.data

import com.google.gson.annotations.SerializedName

data class ForecastResult(
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

data class HourlyUnits(
    val time: String,
    @SerializedName("temperature_2m") val temperatureIUnit: String,
    @SerializedName("relativehumidity_2m") val humidityUnit: String,
    val rain: String,
    val cloudcover: String,
    @SerializedName("windspeed_10m") val windSpeedUnit: String,
    @SerializedName("uv_index") val uvIndexUnit: String,
)

data class CurrentWeather(
    val temperature: Double,
    val windSpeed: Double,
    val weathercode: Int,
    @SerializedName("is_day") val isDay: Int,
    val time: String
)
