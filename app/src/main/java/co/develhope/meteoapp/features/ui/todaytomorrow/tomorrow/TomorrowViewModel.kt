package co.develhope.meteoapp.features.ui.todaytomorrow.tomorrow

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

class TomorrowViewModel : ViewModel() {

    private val todayTomorrowRepository = TodayTomorrowRepository()

    private val _forecastLiveData = MutableLiveData<WeatherConditions>()

    val forecastLiveData: LiveData<WeatherConditions> = _forecastLiveData

    @RequiresApi(Build.VERSION_CODES.O)
    fun downloadForecastInfo(sharedPreferencesHelper: SharedPreferencesHelper) {
        viewModelScope.launch {
            try {
                val forecastInfoTomorrow = todayTomorrowRepository.getTomorrowWeatherCondition(sharedPreferencesHelper)
                _forecastLiveData.postValue(forecastInfoTomorrow)
            } catch (e: Exception) {
//               TODO ADD ERROR MANAGEMENT
            }
        }
    }
}