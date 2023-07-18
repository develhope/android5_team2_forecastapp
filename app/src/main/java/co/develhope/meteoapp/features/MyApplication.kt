package co.develhope.meteoapp.features

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.appModule
import co.develhope.meteoapp.features.ui.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// Serve per utilizzare le shared preferences
class MyApplication:Application() {
    //lateinit var preferences: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
/*        preferences = getSharedPreferences("app", Context.MODE_PRIVATE)
        preferences.edit().putFloat("latitude",37.49F).apply()
        preferences.edit().putFloat("longitude",15.07F).apply()*/

        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(appModule, viewModels))
        }
    }
}