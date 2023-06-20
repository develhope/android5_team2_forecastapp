package co.develhope.meteoapp.features.data

import co.develhope.meteoapp.features.home.domain.Hourly
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
    val hourly: Hourly2,
    @SerializedName("current_weather") val currentWeather: CurrentWeather,
)
