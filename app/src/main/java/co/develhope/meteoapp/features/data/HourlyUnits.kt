package co.develhope.meteoapp.features.data

import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    val time: String,
    @SerializedName("temperature_2m") val temperatureIUnit: String,
    @SerializedName("relativehumidity_2m") val humidityUnit: String,
    val rain: String,
    val cloudcover: String,
    @SerializedName("windspeed_10m") val windSpeedUnit: String,
    @SerializedName("uv_index") val uvIndexUnit: String,
    )
