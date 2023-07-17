package co.develhope.meteoapp.features.ui.todaytomorrow.today

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import co.develhope.meteoapp.features.data.remote.repositories.TodayTomorrowRepository
import kotlinx.coroutines.launch

class TodayViewModel : ViewModel() {

    private val todayTomorrowRepository = TodayTomorrowRepository()

    private val _forecastLiveData = MutableLiveData<WeatherConditions>()

    val forecastLiveData: LiveData<WeatherConditions> = _forecastLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadForecastInfo(sharedPreferencesHelper: SharedPreferencesHelper){
        viewModelScope.launch {
            try {
                val forecastInfo = todayTomorrowRepository.getHourlyWeatherCondition(sharedPreferencesHelper)
                _forecastLiveData.postValue(forecastInfo)
            } catch (e: Exception){
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}