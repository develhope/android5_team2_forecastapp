package co.develhope.meteoapp.features.data.local

import android.content.SharedPreferences
import co.develhope.meteoapp.features.data.remote.models.City
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//questa classe gestisce le shared preferences

class SharedPreferencesHelper(private val preferences: SharedPreferences) {
    companion object {
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"
        const val KEY_CITY_NAME = "city name"
        const val KEY_COUNTRY = "country"
        const val KEY_RECENTLY_SEARCHED = "recently searched cities"
        const val KEY_CLICKED_CITY = "clicked city"
    }
    private val gson = Gson()

    fun saveClickedCity (clickedCity: City){
        val json = gson.toJson(clickedCity)
        preferences.edit().putString(KEY_CLICKED_CITY,json).apply()
        saveRecentlySearchedCities()
    }

    private fun getClickedCity(): City{
        val json = preferences.getString(KEY_CLICKED_CITY, null)
        val type = object : TypeToken<City>() {}.type
        return gson.fromJson(json,type)
    }

    private fun saveRecentlySearchedCities(){
        val recentlySearched = getRecentlySearchedCities()
        if (!recentlySearched.contains(getClickedCity())){
            recentlySearched.add(getClickedCity())
        }
        if(recentlySearched.size > 5){
            recentlySearched.removeAt(0)
        }
        val json = gson.toJson(recentlySearched)
        preferences.edit().putString(KEY_RECENTLY_SEARCHED, json).apply()
    }

    fun saveLatitude(latitude: Double) {
        preferences.edit().putString(KEY_LATITUDE, latitude.toString()).apply()
    }

    fun saveLongitude(longitude: Double) {
        preferences.edit().putString(KEY_LONGITUDE, longitude.toString()).apply()
    }

    fun saveCityName(name: String?) {
        preferences.edit().putString(KEY_CITY_NAME, name).apply()
    }

    fun saveCountry(country: String) {
        preferences.edit().putString(KEY_COUNTRY, country).apply()
    }

    fun getRecentlySearchedCities(): MutableList<City>{
        val json = preferences.getString(KEY_RECENTLY_SEARCHED,null)
        val type = object : TypeToken<List<City>>() {}.type
        return gson.fromJson(json,type) ?: mutableListOf()
    }

    fun getLatitude(): Double? {
        val latitudeString = preferences.getString(KEY_LATITUDE, null)
        return latitudeString?.toDouble()
    }

    fun getLongitude(): Double? {
        val longitudeString = preferences.getString(KEY_LONGITUDE, null)
        return longitudeString?.toDouble()
    }

    fun getCityName(): String? {
        return preferences.getString(KEY_CITY_NAME, null)
    }

    fun getCountry(): String? {
        return preferences.getString(KEY_COUNTRY, null)
    }
}