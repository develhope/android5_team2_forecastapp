package co.develhope.meteoapp.features

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

// Serve per utilizzare le shared preferences
class MyApplication:Application() {
    lateinit var preferences: SharedPreferences

    override fun onCreate() {
        super.onCreate()
        preferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        preferences.edit().putFloat("latitude",37.49F).apply()
        preferences.edit().putFloat("longitude",15.07F).apply()
    }
}