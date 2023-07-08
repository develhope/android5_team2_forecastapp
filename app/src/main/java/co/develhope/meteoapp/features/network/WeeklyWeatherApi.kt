package co.develhope.meteoapp.features.network

import co.develhope.meteoapp.features.home.domain.WeatherConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface WeeklyWeatherApi {
    @GET("v1/forecast")
    suspend fun getWeeklyMeteo(
//        ho aggiunto i nullable su latitude e longitude
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("daily") daily: String,
        @Query("current_weather") currentWeather: Boolean,
        @Query("timezone") timezone: String
    ): WeatherConditions
}