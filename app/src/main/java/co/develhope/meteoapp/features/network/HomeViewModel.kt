package co.develhope.meteoapp.features.network

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.features.home.domain.WeatherConditions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.OffsetDateTime

private const val BASE_URL = "https://api.open-meteo.com/"

@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel : ViewModel() {

    private val weeklyWeatherApi: WeeklyWeatherApi
    private val _weeklyWeatherLiveData = MutableLiveData<WeatherConditions>()
    val weeklyWeatherLiveData: LiveData<WeatherConditions> = _weeklyWeatherLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    private fun provideGson(): Gson = GsonBuilder()
        .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
        .create()
    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(provideGson())
        ).build()

        weeklyWeatherApi = retrofit.create(WeeklyWeatherApi::class.java)
    }

    fun retrieveMeteo(
//        TODO         add a way to insert the correct query parameters needed, currently using Catania params
//        latitude: Double,
//        longitude: Double,
//        daily: String,
//        currentWeather: Boolean,
//        timezone: String
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val meteo = weeklyWeatherApi.getWeeklyMeteo(
                    37.49 ,
                    15.07,
                    "weathercode,temperature_2m_max,temperature_2m_min,rain_sum,windspeed_10m_max",
                    true,
                    "Europe/Berlin"
                )
                _weeklyWeatherLiveData.postValue(meteo)
            } catch (e: Exception){
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }

}