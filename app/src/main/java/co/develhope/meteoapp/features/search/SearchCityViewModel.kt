package co.develhope.meteoapp.features.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.features.search.domain.SearchCityResult
import co.develhope.meteoapp.features.search.network.SearchCityApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://geocoding-api.open-meteo.com/"

class SearchCityViewModel : ViewModel() {
    private val searchCityApi: SearchCityApi

    private val _searchCitiesLiveData = MutableLiveData<SearchCityResult>()
    val searchCitiesLiveData: LiveData<SearchCityResult> = _searchCitiesLiveData


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        searchCityApi = retrofit.create(SearchCityApi::class.java)
    }

    fun getCities(p0: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val city = searchCityApi.getGeoLocalization(p0)
            _searchCitiesLiveData.postValue(city)
        }
    }
}