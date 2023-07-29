package co.develhope.meteoapp.features.data.local

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import co.develhope.meteoapp.R
import com.google.android.gms.location.LocationServices

object GeoLocalizationHelper {
    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager: LocationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(context: Context): Boolean {
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

    private fun requestPermissions(activity: AppCompatActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            100
        )
    }

    @SuppressLint("MissingPermission", "SetTextI18n")
    fun getCurrentLocation(activity: AppCompatActivity,sharedPreferencesHelper: SharedPreferencesHelper) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (checkPermissions(activity)) {
            Log.d("main","permissions granted??")
            if (isLocationEnabled(activity)) {
                Log.d("main","location enabled?")
                fusedLocationClient.lastLocation.addOnCompleteListener(activity) { task ->
                    val location: Location? = task.result
                    Log.d("main","$location is nul??")
                    if (location != null) {
                        saveLocation(activity,sharedPreferencesHelper,location)
                    }
                }
            } else {
                Toast.makeText(activity, activity.getString(R.string.request_turn_on_position), Toast.LENGTH_LONG).show()
//                serve a chiedere all'utente di attivare la posizione
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
            }
        } else {
            requestPermissions(activity)
        }
    }

    private fun saveLocation(context: Context,sharedPreferencesHelper: SharedPreferencesHelper, location:Location){
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