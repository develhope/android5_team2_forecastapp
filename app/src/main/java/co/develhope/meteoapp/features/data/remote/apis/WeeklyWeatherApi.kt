package co.develhope.meteoapp.features.data.remote.apis

import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface WeeklyWeatherApi {
    @GET("v1/forecast")
    suspend fun getWeeklyMeteo(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("daily") daily: String,
        @Query("current_weather") currentWeather: Boolean,
        @Query("timezone") timezone: String
    ): WeatherConditions

}
