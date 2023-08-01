package co.develhope.meteoapp.features.data.remote.repositories

import co.develhope.meteoapp.features.data.remote.apis.SearchCityApi


class SearchRepository(
    private val searchCityApi: SearchCityApi
){

    suspend fun getCity(p0: String?)= searchCityApi.getGeoLocalization(p0)
}