package co.develhope.meteoapp.features.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastAPI {
    @GET("v1/forecast?latitude=52.52&" +
            "longitude=13.41&" +
            "hourly=temperature_2m,relativehumidity_2m,dewpoint_2m,apparent_temperature,rain,weathercode,cloudcover,windspeed_10m,uv_index,is_day,winddirection_10m" +
            "&timezone=auto&" +
            "current_weather=true")
    suspend fun getForecast(
        @Query("start_date") start_date : String,
        @Query("end_date") end_date : String
    ): ForecastResult
}

