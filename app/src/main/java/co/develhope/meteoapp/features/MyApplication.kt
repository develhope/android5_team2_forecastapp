package co.develhope.meteoapp.features

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import co.develhope.meteoapp.features.data.appModule
import co.develhope.meteoapp.features.ui.viewModels
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication:Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(appModule, viewModels))
        }
    }
}