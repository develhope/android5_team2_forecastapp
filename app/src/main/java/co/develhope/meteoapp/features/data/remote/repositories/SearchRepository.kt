package co.develhope.meteoapp.features.data.remote.repositories

import co.develhope.meteoapp.features.data.remote.apis.SearchCityApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRepository(
    private val searchCityApi: SearchCityApi
){

    suspend fun getCity(p0: String?)= searchCityApi.getGeoLocalization(p0)
}