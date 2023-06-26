package co.develhope.meteoapp.features.data

import androidx.core.app.NotificationCompat.Action.SemanticAction
import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    val temperature: Double,
    val windSpeed: Double,
    val weathercode: Int,
    @SerializedName("is_day") val isDay: Int,
    val time: String
)
