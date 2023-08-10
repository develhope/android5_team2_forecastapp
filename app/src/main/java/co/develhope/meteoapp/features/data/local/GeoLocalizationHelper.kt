package co.develhope.meteoapp.features.data.local

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

object GeoLocalizationHelper {
     fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

     fun checkPermissions(context: Context): Boolean {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

     fun requestPermissions(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            100
        )
    }


     fun saveLocation(context: Context,sharedPreferencesHelper: SharedPreferencesHelper, location:Location){
        Log.d("main","${location.latitude}")
        sharedPreferencesHelper.saveLongitude(location.longitude)
        sharedPreferencesHelper.saveLatitude(location.latitude)
        Log.d("main","${sharedPreferencesHelper.getLatitude()}")
        val geo = Geocoder(context).getFromLocation(location.latitude,location.longitude,1)
        val country = geo?.firstOrNull()?.adminArea.orEmpty()
        val city = geo?.firstOrNull()?.locality.orEmpty()
        sharedPreferencesHelper.saveCountry(country)
        sharedPreferencesHelper.saveCityName(city)
    }
}