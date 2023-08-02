package co.develhope.meteoapp.features.data.remote.apis

import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getWeeklyMeteo(
        @Query("latitude") latitude: Double?,
        @Query("longitude") longitude: Double?,
        @Query("daily") daily: String,
        @Query("current_weather") currentWeather: Boolean,
        @Query("timezone") timezone: String
    ): WeatherConditions

    @GET("v1/forecast?"+"hourly=temperature_2m,relativehumidity_2m,precipitation_probability,apparent_temperature,rain,weathercode,cloudcover,windspeed_10m,uv_index,is_day,winddirection_10m")
    suspend fun getForecast(
        @Query("latitude") latitude: Double? =52.52,
        @Query("longitude") longitude: Double? =13.41,
        @Query("current_weather") current_weather: Boolean = true,
        @Query("timezone") timezone : String = "auto",
        @Query("start_date") start_date : String,
        @Query("end_date") end_date : String
    ): WeatherConditions

}
