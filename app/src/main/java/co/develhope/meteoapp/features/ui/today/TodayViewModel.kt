package co.develhope.meteoapp.features.ui.today

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

class TodayViewModel(
    private val todayTomorrowRepository: WeatherRepository
    ) : ViewModel() {

    private val _weatherLiveData = MutableLiveData<WeatherConditions>()

    val weatherLiveData: LiveData<WeatherConditions> = _weatherLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadWeatherInfo(sharedPreferencesHelper: SharedPreferencesHelper){
        viewModelScope.launch {
            try {
                val weatherInfo = todayTomorrowRepository.getTodayHourlyWeather(sharedPreferencesHelper)
                _weatherLiveData.postValue(weatherInfo)
            } catch (e: Exception){
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}