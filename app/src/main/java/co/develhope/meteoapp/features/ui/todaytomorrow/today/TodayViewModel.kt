package co.develhope.meteoapp.features.ui.todaytomorrow.today

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import co.develhope.meteoapp.features.data.remote.repositories.WeatherRepository
import kotlinx.coroutines.launch

class TodayViewModel(private val todayTomorrowRepository: WeatherRepository) : ViewModel() {

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