package co.develhope.meteoapp.features.today

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.data.ForecastAPI
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.network.DateUtils
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.Calendar

class TodayViewModel : ViewModel() {

    private val forecastAPI: ForecastAPI

    private val _forecastLiveData = MutableLiveData<ForecastResult>()

    val forecastLiveData: LiveData<ForecastResult> = _forecastLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    private val today = DateUtils.getYearMonthAndDay("${LocalDate.now()}")

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastAPI = retrofit.create(ForecastAPI::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadForecastInfo(){
        viewModelScope.launch {
            try {
                Log.d("todayDate","${LocalDate.now()}")
                val forecastInfo = forecastAPI.getForecast(today,today)
                _forecastLiveData.postValue(forecastInfo)
            } catch (e: Exception){
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}