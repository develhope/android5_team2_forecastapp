package co.develhope.meteoapp.features.data.remote.repositories

import co.develhope.meteoapp.features.data.remote.apis.SearchCityApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchRepository {

    private val searchCityApi: SearchCityApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        searchCityApi = retrofit.create(SearchCityApi::class.java)
    }

    suspend fun getCity(p0: String?)= searchCityApi.getGeoLocalization(p0)
}