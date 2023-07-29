package co.develhope.meteoapp.features.ui.home

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

class HomeViewModel(
    private val homeRepository : WeatherRepository
) : ViewModel() {

    private val _weeklyWeatherLiveData = MutableLiveData<WeatherConditions>()

    val weeklyWeatherLiveData: LiveData<WeatherConditions> = _weeklyWeatherLiveData


    @RequiresApi(Build.VERSION_CODES.O)
    fun retrieveMeteo(sharedPreferencesHelper: SharedPreferencesHelper) {
        viewModelScope.launch {
            try {
                val meteo = homeRepository.getHomeWeather(sharedPreferencesHelper)
                _weeklyWeatherLiveData.postValue(meteo)
            } catch (e: Exception) {
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}
