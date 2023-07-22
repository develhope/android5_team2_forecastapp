package co.develhope.meteoapp.features

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Geocoder.GeocodeListener
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ActivityMainBinding
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreferencesHelper = SharedPreferencesHelper(
            getSharedPreferences(
                "MyPrefs",
                MODE_PRIVATE
            )
        )


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)
        if(isGooglePlayServicesAvailable()){
            checkLocationPermission(sharedPreferencesHelper)
        }
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                    fusedLocationClient.lastLocation.addOnFailureListener {
                        Log.d("MainActivity","nada $it")
                    }
                    fusedLocationClient.lastLocation.addOnSuccessListener {
//                        sharedPreferencesHelper.saveLatitude(it.latitude)
//                        sharedPreferencesHelper.saveLongitude(it.longitude)
                        Log.d("MainActivity","yessa, $it ")
                    }
                    Log.d("MainActivity","access to fine location")
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                    Log.d("MainActivity","access to coarse location")
                } else -> {
                // No location access granted.
                Log.d("MainActivity","no access to location")
            }
            }
        }
        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))
//
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this)

        if (resultCode == ConnectionResult.SUCCESS) {
            // Google Play Services are available, proceed with location retrieval
            Log.d("Mainactivity","success")
        } else {
            // Google Play Services are not available, handle the error
            Log.d("Mainactivity","failure")
        }
        Log.d("mainactivity","shared latitude ${sharedPreferencesHelper.getLatitude()}")
    }


    private fun isGooglePlayServicesAvailable(): Boolean {
        val googleApiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this)
        return resultCode == ConnectionResult.SUCCESS
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun checkLocationPermission(sharedPreferencesHelper: SharedPreferencesHelper) {
        val fineLocationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val coarseLocationPermission = Manifest.permission.ACCESS_COARSE_LOCATION

        if (ContextCompat.checkSelfPermission(this, fineLocationPermission) == PackageManager.PERMISSION_GRANTED) {
            // Permission already granted, proceed with location retrieval.
            fusedLocationClient.lastLocation.addOnSuccessListener {
                if (it != null) {
                    // Do something with the location
                    sharedPreferencesHelper.saveLongitude(it.longitude)
                    sharedPreferencesHelper.saveLatitude(it.latitude)
                    Log.d("MainActivity", "Location retrieved: $it, ${it.latitude},${it.longitude}")
                    Log.d("mainactivity","shared dentro la funzione latitude ${sharedPreferencesHelper.getLatitude()}")
                    val geo = Geocoder(this).getFromLocation(it.latitude,it.longitude,1)
                    val place = geo?.firstOrNull()?.locality.orEmpty()
                    val region = geo?.firstOrNull()?.countryName.orEmpty()
                    Log.d("mainact","place: $place, $region")
                    sharedPreferencesHelper.saveCountry(place)
                    sharedPreferencesHelper.saveCityName(region)

                } else {
                    // Location is null, handle the case
                    Log.d("MainActivity", "Location is null.")
                }
            }.addOnFailureListener { exception: Exception ->
                // Handle the failure, if any
                Log.d("MainActivity", "Location retrieval failed: $exception")
            }
        } else {
            // Permission not granted, request it from the user.
//            locationPermissionRequest.launch(arrayOf(fineLocationPermission))

        }
    }


}


