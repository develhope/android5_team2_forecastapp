package co.develhope.meteoapp.features.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.develhope.meteoapp.features.data.remote.models.SearchCityResult
import co.develhope.meteoapp.features.data.remote.repositories.SearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchCityViewModel(
    private val searchRepository: SearchRepository

) : ViewModel() {


    private val _searchCitiesLiveData = MutableLiveData<SearchCityResult>()

    val searchCitiesLiveData: LiveData<SearchCityResult> = _searchCitiesLiveData

    fun getCities(p0: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val city = searchRepository.getCity(p0)
            _searchCitiesLiveData.postValue(city)
        }
    }
}