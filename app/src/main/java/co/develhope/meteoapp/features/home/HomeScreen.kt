package co.develhope.meteoapp.features.home

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
import co.develhope.meteoapp.features.home.domain.HomeScreenAdapter
import co.develhope.meteoapp.databinding.ScreenHomeBinding
import co.develhope.meteoapp.features.home.domain.WeatherConditions
import co.develhope.meteoapp.features.network.DateUtils.getMonthAndDay
import co.develhope.meteoapp.features.network.HomeViewModel
import java.util.*

class HomeScreen : Fragment() {
    private lateinit var binding: ScreenHomeBinding
    private val viewModel: HomeViewModel by viewModels()

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
        viewModel.retrieveMeteo()
        viewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner) {
            showMeteo(it)
            showTodaysMeteo(it)
        }
    }

    fun showTodaysMeteo(item: WeatherConditions, position: Int = 0) {
        val maxTemperature = item.daily.maxTemperature[position]
        val minTemperature = item.daily.minTemperature[position]
        val date = item.daily.time[position]
        val rain = item.daily.rainSum[position]
        val wind = item.daily.windMax[position]
        val monthAndDay = getMonthAndDay(date)
        binding.homeToday.currentDay.setText(R.string.today)
        binding.homeToday.maxTemperatureValue.text = maxTemperature.toString().plus("°")
        binding.homeToday.minTemperatureValue.text = minTemperature.toString().plus("°")
        binding.homeToday.dayAndMonth.text = monthAndDay
        binding.homeToday.rainfallValue.text = rain.toString().plus("mm")
        binding.homeToday.windValue.text = wind.toString().plus("km/h")
    }

    fun showMeteo(meteo: WeatherConditions) {
        binding.recyclerView.adapter = HomeScreenAdapter(meteo)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

}
