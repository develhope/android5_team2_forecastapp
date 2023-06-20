package co.develhope.meteoapp.features.data

import com.google.gson.annotations.SerializedName

data class Hourly2(
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

    )

