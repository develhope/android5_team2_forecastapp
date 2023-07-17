package co.develhope.meteoapp.features.data.remote.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.local.OffsetDateTimeTypeAdapter
import co.develhope.meteoapp.features.data.remote.apis.WeeklyWeatherApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
class HomeRepository {

    private val weeklyWeatherApi: WeeklyWeatherApi

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/").addConverterFactory(
            GsonConverterFactory.create(provideGson())
        ).build()

        weeklyWeatherApi = retrofit.create(WeeklyWeatherApi::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .create()

    suspend fun getHomeWeather(defLatitude: Double? = 0.0, defLongitude: Double? = 0.0)
        = weeklyWeatherApi.getWeeklyMeteo(
            defLatitude,
            defLongitude,
            "weathercode,temperature_2m_max,temperature_2m_min,rain_sum,windspeed_10m_max",
            true,
            "Europe/Berlin"
        )
}