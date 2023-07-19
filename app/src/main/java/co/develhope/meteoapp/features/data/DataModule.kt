package co.develhope.meteoapp.features.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.local.OffsetDateTimeTypeAdapter
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.apis.SearchCityApi
import co.develhope.meteoapp.features.data.remote.apis.WeatherApi
import co.develhope.meteoapp.features.data.remote.repositories.WeatherRepository
import co.develhope.meteoapp.features.data.remote.repositories.SearchRepository
import com.google.gson.GsonBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.OffsetDateTime

@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {
    single { SharedPreferencesHelper(androidContext().getSharedPreferences("app", Context.MODE_PRIVATE)) }
    single { WeatherRepository(get())}
    single { SearchRepository(get()) }
    single {   val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
            .registerTypeAdapter(OffsetDateTime::class.java, OffsetDateTimeTypeAdapter())
            .create()))
        .build()

        retrofit.create(WeatherApi::class.java)}

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(SearchCityApi::class.java)
    }
}



