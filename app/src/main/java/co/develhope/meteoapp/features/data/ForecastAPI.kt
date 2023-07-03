package co.develhope.meteoapp.features.data

import retrofit2.http.GET

interface ForecastAPI {
    @GET("v1/forecast?latitude=52.52&" +
            "longitude=13.41&" +
            "hourly=temperature_2m,relativehumidity_2m,dewpoint_2m,apparent_temperature,rain,weathercode,cloudcover,windspeed_10m,uv_index,is_day" +
            "&timezone=auto&" +
            "current_weather=true")
    suspend fun getForecast(): ForecastResult
}