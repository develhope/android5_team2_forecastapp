package co.develhope.meteoapp.features.data.remote.models

data class SearchCityResult(val results: List<City>)

data class City(
    val country: String,
    val admin1: String,
    val name: String?,
    val latitude: Double,
    val longitude: Double
    )