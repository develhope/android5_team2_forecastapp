package co.develhope.meteoapp.features.today

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.data.ForecastAPI
import co.develhope.meteoapp.features.data.ForecastResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TodayViewModel : ViewModel() {

    private val forecastAPI: ForecastAPI

    private val _forecastLiveData = MutableLiveData<ForecastResult>()

    val forecastLiveData: LiveData<ForecastResult> = _forecastLiveData

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastAPI = retrofit.create(ForecastAPI::class.java)
    }

    fun downloadForecastInfo(){
        viewModelScope.launch {
            try {
                val meteoInfo = forecastAPI.getForecast()
                _forecastLiveData.postValue(meteoInfo)
            } catch (e: Exception){
//               TODO ADD ERROR MANAGEMENT
            }
        }

    }

}