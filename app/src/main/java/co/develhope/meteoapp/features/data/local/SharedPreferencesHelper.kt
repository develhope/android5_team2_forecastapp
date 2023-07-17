package co.develhope.meteoapp.features.data.local

import android.content.SharedPreferences
//questa classe gestisce le shared preferences

class SharedPreferencesHelper(private val preferences: SharedPreferences) {
    companion object {
        const val KEY_LATITUDE = "latitude"
        const val KEY_LONGITUDE = "longitude"
        const val KEY_CITY_NAME = "city name"
        const val KEY_COUNTRY = "country"
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