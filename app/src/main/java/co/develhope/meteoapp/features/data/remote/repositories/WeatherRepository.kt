package co.develhope.meteoapp.features.data.remote.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.local.DateUtils
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.apis.WeatherApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class WeatherRepository(
    private val weatherApi: WeatherApi
) {


    suspend fun getHomeWeather(defLatitude: Double? = 0.0, defLongitude: Double? = 0.0)
        = weatherApi.getWeeklyMeteo(
            defLatitude,
            defLongitude,
            "weathercode,temperature_2m_max,temperature_2m_min,rain_sum,windspeed_10m_max",
            true,
            "Europe/Berlin"
        )

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = DateUtils.getYearMonthAndDay("${LocalDate.now()}")

    @RequiresApi(Build.VERSION_CODES.O)
    private val tomorrow = DateUtils.getYearMonthAndDay("${LocalDate.now().plusDays(1)}")

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getHourlyWeatherCondition(sharedPreferencesHelper: SharedPreferencesHelper) =
        weatherApi.getForecast(
            sharedPreferencesHelper.getLatitude(),
            sharedPreferencesHelper.getLongitude(),
            true ,
            "auto",
            today,
            today
        )

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTomorrowWeatherCondition(sharedPreferencesHelper: SharedPreferencesHelper) =
        weatherApi.getForecast(
            sharedPreferencesHelper.getLatitude(),
            sharedPreferencesHelper.getLongitude(),
            true ,
            "auto",
            tomorrow,
            tomorrow
        )
}