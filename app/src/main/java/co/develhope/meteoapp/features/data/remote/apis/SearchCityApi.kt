package co.develhope.meteoapp.features.data.remote.apis

import co.develhope.meteoapp.features.data.remote.models.SearchCityResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchCityApi {
    @GET("v1/search")
    suspend fun getGeoLocalization(@Query("name") name: String?): SearchCityResult
}