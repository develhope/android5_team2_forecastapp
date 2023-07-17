package co.develhope.meteoapp.features.data.remote.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.local.DateUtils
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.apis.HourlyWeatherAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class TodayTomorrowRepository {

    private val hourlyWeatherAPI: HourlyWeatherAPI

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        hourlyWeatherAPI = retrofit.create(HourlyWeatherAPI::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = DateUtils.getYearMonthAndDay("${LocalDate.now()}")

    @RequiresApi(Build.VERSION_CODES.O)
    private val tomorrow = DateUtils.getYearMonthAndDay("${LocalDate.now().plusDays(1)}")

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getHourlyWeatherCondition(sharedPreferencesHelper: SharedPreferencesHelper) =
        hourlyWeatherAPI.getForecast(
            sharedPreferencesHelper.getLatitude(),
            sharedPreferencesHelper.getLongitude(),
            true ,
            "auto",
            today,
            today
        )

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getTomorrowWeatherCondition(sharedPreferencesHelper: SharedPreferencesHelper) =
        hourlyWeatherAPI.getForecast(
            sharedPreferencesHelper.getLatitude(),
            sharedPreferencesHelper.getLongitude(),
            true ,
            "auto",
            tomorrow,
            tomorrow
        )
}