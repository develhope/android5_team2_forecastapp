package co.develhope.meteoapp.features.ui.main

import android.view.Gravity
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ActivityMainBinding
import co.develhope.meteoapp.features.ui.error_message.ConnectivityObserver
import co.develhope.meteoapp.features.ui.error_message.NetworkConnectivityObserver
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import co.develhope.meteoapp.features.data.local.GeoLocalizationHelper
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {
  
    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val sharedPreferencesHelper: SharedPreferencesHelper by inject()
    private lateinit var connectivityObserver: ConnectivityObserver
    private lateinit var popupWindow: PopupWindow

    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        GeoLocalizationHelper.getCurrentLocation(this, sharedPreferencesHelper)
        getGeoLocalization()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .setupWithNavController(navController)

        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        fun observeNetworkStatus(): Flow<ConnectivityObserver.Status> {
            return connectivityObserver.observe()
        }

        observeNetworkStatus().onEach { status ->
            when (status) {
                ConnectivityObserver.Status.Available -> {
                    showToast("There is network connection")
                    hidePopup()
                }

                ConnectivityObserver.Status.Unavailable -> {
                    showPopup()
                    showToast("No network connection")
                }

                ConnectivityObserver.Status.Losing -> {
                    showPopup()
                }

                ConnectivityObserver.Status.Lost -> {
                    showToast("No network connection")
                    showPopup()

                }
            }
        }.launchIn(lifecycleScope)

    }

    fun isGooglePlayServicesAvailable(): Boolean {
        Log.e("Main", "we are checking playservice availibalet: ")
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
        return resultCode == ConnectionResult.SUCCESS
    }

    fun getGeoLocalization() {
        if (isGooglePlayServicesAvailable()) {
            try {
                GeoLocalizationHelper.getCurrentLocation(this, sharedPreferencesHelper)
                Log.e("Main", "Succesfully getting location: ")
            } catch (e: Exception) {
                Log.e("Main", "Error getting location: ${e.message}")
            }
        } else {
            // Google Play Services are not available
            Log.e("Main", "Google Play Services are not available on this device.")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun showPopup() {
        val inflater = LayoutInflater.from(this)
        val popupView = inflater.inflate(R.layout.fragment_error, null, false)
        val image = popupView.findViewById<ImageView>(R.id.error_image)
        image.setImageResource(R.drawable.error_message)

        popupWindow = PopupWindow(
            popupView,
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT,
            false
        )

        popupWindow.animationStyle = R.style.dialogAnimation

        popupWindow.isFocusable = false

        popupWindow.showAtLocation(
            this.findViewById(R.id.error_layout),
            Gravity.CENTER,
            0,
            0
        )
    }

    private fun hidePopup() {
        if (::popupWindow.isInitialized && popupWindow.isShowing) {
            popupWindow.dismiss()
        }
    }

}