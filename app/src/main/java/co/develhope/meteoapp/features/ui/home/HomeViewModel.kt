package co.develhope.meteoapp.features.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import co.develhope.meteoapp.features.data.remote.repositories.HomeRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val homeRepository = HomeRepository()

    private val _weeklyWeatherLiveData = MutableLiveData<WeatherConditions>()

    val weeklyWeatherLiveData: LiveData<WeatherConditions> = _weeklyWeatherLiveData

    //    ho cambiato un po' la funziona per avere in ingresso latitude and longitude, al primissimo avvio dell'app, la chiamata va a 0 0
    @RequiresApi(Build.VERSION_CODES.O)
    fun retrieveMeteo(defLatitude: Double? = 0.0, defLongitude: Double? = 0.0) {
        viewModelScope.launch {
            try {
                val meteo = homeRepository.getHomeWeather(defLatitude,defLongitude)
                _weeklyWeatherLiveData.postValue(meteo)
            } catch (e: Exception) {
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}
