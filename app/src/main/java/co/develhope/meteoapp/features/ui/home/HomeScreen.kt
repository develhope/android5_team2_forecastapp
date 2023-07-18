package co.develhope.meteoapp.features.ui.home

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenHomeBinding
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import co.develhope.meteoapp.features.data.local.DateUtils.getMonthAndDay
import co.develhope.meteoapp.features.ui.todaytomorrow.today.TodayViewModel
import org.koin.android.ext.android.inject
import java.util.*

class HomeScreen : Fragment() {
    private lateinit var binding: ScreenHomeBinding

    private val viewModel: HomeViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenHomeBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //        dichiara shared preferences Helper per utilizzarlo
        val sharedPreferencesHelper = SharedPreferencesHelper(
            requireContext().getSharedPreferences(
                "MyPrefs",
                Context.MODE_PRIVATE
            )
        )
//        shared preferences per usare e mostrare i dati
        val latitude = sharedPreferencesHelper.getLatitude()
        val longitude = sharedPreferencesHelper.getLongitude()
        viewModel.retrieveMeteo(latitude, longitude)
        viewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner) {
            showMeteo(it)
            showTodaysMeteo(it,sharedPreferencesHelper)
        }
    }

    fun showTodaysMeteo(item: WeatherConditions, sharedPreferencesHelper: SharedPreferencesHelper, position: Int = 0) {
        val maxTemperature = item.daily.maxTemperature[position].toInt()
        val minTemperature = item.daily.minTemperature[position].toInt()
        val date = item.daily.time[position]
        val rain = item.daily.rainSum[position]
        val wind = item.daily.windMax[position]
        val monthAndDay = getMonthAndDay(date)
        val cityName = sharedPreferencesHelper.getCityName()
        val country = sharedPreferencesHelper.getCountry()
        binding.homeToday.currentDay.setText(R.string.today)
        binding.homeToday.maxTemperatureValue.text = maxTemperature.toString().plus("°")
        binding.homeToday.minTemperatureValue.text = minTemperature.toString().plus("°")
        binding.homeToday.dayAndMonth.text = monthAndDay
        binding.homeToday.rainfallValue.text = rain.toString().plus("mm")
        binding.homeToday.windValue.text = wind.toString().plus("km/h")
        binding.cityName.text = cityName.plus(", $country")
    }

    fun showMeteo(meteo: WeatherConditions) {
        binding.recyclerView.adapter = HomeScreenAdapter(meteo)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

}
