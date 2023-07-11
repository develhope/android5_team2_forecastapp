package co.develhope.meteoapp.features.tomorrow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.ForecastAPI
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.network.DateUtils
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class TomorrowViewModel : ViewModel() {

    private val forecastAPI: ForecastAPI

    private val _forecastLiveData = MutableLiveData<ForecastResult>()

    val forecastLiveData: LiveData<ForecastResult> = _forecastLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    private val tomorrowDate = DateUtils.getYearMonthAndDay("${LocalDate.now().plusDays(1)}")

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastAPI = retrofit.create(ForecastAPI::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadForecastInfo(sharedPreferencesHelper: SharedPreferencesHelper) {
        viewModelScope.launch {
            try {
                val forecastInfoTomorrow = forecastAPI.getForecast(
                    sharedPreferencesHelper.getLatitude(),
                    sharedPreferencesHelper.getLongitude(),
                    true ,
                    "auto",
                    tomorrowDate,
                    tomorrowDate)
                _forecastLiveData.postValue(forecastInfoTomorrow)
            } catch (e: Exception) {
//               TODO ADD ERROR MANAGEMENT
            }
        }

    }
}