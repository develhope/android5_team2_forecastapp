package co.develhope.meteoapp.features.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenSearchBinding
import co.develhope.meteoapp.features.data.local.GeoLocalizationHelper
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.City
import co.develhope.meteoapp.features.data.remote.models.SearchCityResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject

class SearchScreen : Fragment(), OnItemClickListener {
    private lateinit var binding: ScreenSearchBinding

    private val viewModel: SearchCityViewModel by inject()
    private lateinit var locationCallback: LocationCallback

    private val sharedPreferencesHelper: SharedPreferencesHelper by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecentlySearchedCities(sharedPreferencesHelper)

        viewModel.searchCitiesLiveData.observe(viewLifecycleOwner) {
            showCities(it, sharedPreferencesHelper)
        }

        binding.locationButton.setOnClickListener {

            getGeoLocalization()
        }

        binding.searchWidget.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! >= 4) {
                    binding.searchWidget.clearFocus()
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.error_search_too_short),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0.isNullOrEmpty() || p0.length <= 3) {
                    showRecentlySearchedCities(sharedPreferencesHelper)
                } else {
                    viewModel.getCity(p0.trim())
                }
                return true
            }
        })
    }

    private fun showCities(
        city: SearchCityResult,
        sharedPreferencesHelper: SharedPreferencesHelper
    ) {
        if (city.results != null) {
            binding.textviewScreenSearch.visibility = View.GONE
            binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.searchRecyclerview.adapter =
                SearchScreenAdapter(city, city.results, sharedPreferencesHelper, this)
        } else {
            Toast.makeText(
                context,
                getString(R.string.error_search_invalid_city),
                Toast.LENGTH_SHORT
            ).show()
            showRecentlySearchedCities(sharedPreferencesHelper)
        }

    }

    private fun showRecentlySearchedCities(sharedPreferencesHelper: SharedPreferencesHelper) {
        binding.textviewScreenSearch.visibility = View.VISIBLE
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerview.adapter =
            SearchScreenAdapter(
                SearchCityResult(sharedPreferencesHelper.getRecentlySearchedCities().reversed()),
                sharedPreferencesHelper.getRecentlySearchedCities(),
                sharedPreferencesHelper,
                this
            )
    }

    override fun onItemClick(itemData: City) {
        findNavController().navigate(R.id.action_search_screen_to_home_screen)
    }

    private fun isGooglePlayServicesAvailable(): Boolean {
        Log.e("Main", "we are checking play service availability")
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(requireContext())
        return resultCode == ConnectionResult.SUCCESS
    }

    private fun getGeoLocalization() {
        if (isGooglePlayServicesAvailable()) {
            try {
               getCurrentLocation(requireActivity() as AppCompatActivity, sharedPreferencesHelper)
                Log.e("Main", "Successfully getting location: ")
            } catch (e: Exception) {
                Toast.makeText(context,"C'è stato un errore, cerca una città manualmente",Toast.LENGTH_SHORT).show()
                Log.e("Main", "Error getting location: ${e.message}")
            }
        } else {
            // Google Play Services are not available
            Toast.makeText(context,"I Google Play Services non sono disponibili su questo dispositivo",Toast.LENGTH_SHORT).show()
            Log.e("Main", "Google Play Services are not available on this device.")
        }
    }
    @SuppressLint("MissingPermission")
    fun getCurrentLocation(activity: AppCompatActivity, sharedPreferencesHelper: SharedPreferencesHelper) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if (GeoLocalizationHelper.checkPermissions(activity)) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val lastLocation = locationResult.lastLocation
                    Log.d("main","inside onlocationresult is it nul? = $lastLocation")
                    if (lastLocation != null) {
                        GeoLocalizationHelper.saveLocation(
                            activity,
                            sharedPreferencesHelper,
                            lastLocation
                        )
                    }
                    fusedLocationClient.removeLocationUpdates(locationCallback)
                }
            }
            val locationRequest = LocationRequest.create().apply {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
            Log.d("main","permissions granted")
            if (GeoLocalizationHelper.isLocationEnabled(activity)) {
                fusedLocationClient.requestLocationUpdates(
                    locationRequest,
                    locationCallback,
                    Looper.getMainLooper()
                )
                Log.d("main","location enabled")
                Toast.makeText(context,"Prendo la posizione",Toast.LENGTH_SHORT).show()
                fusedLocationClient.lastLocation.addOnSuccessListener {
                    Log.d("main","$it is nul??")
                    if (it != null) {
                        GeoLocalizationHelper.saveLocation(
                            activity,
                            sharedPreferencesHelper,
                            it
                        )
                        findNavController().navigate(R.id.action_search_screen_to_home_screen)
                    }
                }
            } else {
                Toast.makeText(activity, activity.getString(R.string.request_turn_on_position), Toast.LENGTH_LONG).show()
//                serve a chiedere all'utente di attivare la posizione
//                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
//                startActivity(intent)
            }
        } else {
            GeoLocalizationHelper.requestPermissions(activity)
        }
    }
}