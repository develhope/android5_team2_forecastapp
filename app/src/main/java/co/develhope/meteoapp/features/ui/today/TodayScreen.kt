package co.develhope.meteoapp.features.ui.today

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenTodayBinding
import co.develhope.meteoapp.features.data.local.DateUtils
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.ForecastInfo
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import org.koin.android.ext.android.inject
import java.time.LocalDate
import java.time.LocalTime

@Suppress("IMPLICIT_CAST_TO_ANY", "UNUSED_EXPRESSION")
class TodayScreen : Fragment() {

    private lateinit var binding: ScreenTodayBinding

    private val viewModel: TodayViewModel by inject()
    private val sharedPreferencesHelper: SharedPreferencesHelper by inject()

    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate = DateUtils.getDateForTodayAndTomorrowScreen("${LocalDate.now()}")
    @RequiresApi(Build.VERSION_CODES.O)
    private val currentHour = LocalTime.now().hour

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ScreenTodayBinding.inflate(inflater)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (sharedPreferencesHelper.getCityName().isNullOrEmpty()){
            findNavController().navigate(R.id.action_today_screen_to_search_screen)
            Toast.makeText(context,"Search a city!", Toast.LENGTH_SHORT).show()
        }

        viewModel.downloadWeatherInfo(sharedPreferencesHelper)

        viewModel.weatherLiveData.observe(viewLifecycleOwner){
            showTodayWeather(it)
            scrollToCurrentHour(it)
        }

        getCityName(sharedPreferencesHelper)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showTodayWeather(meteo: WeatherConditions){
        binding.todayRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.todayRecyclerView.adapter = TodayAdapter(meteo)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun scrollToCurrentHour(meteo: WeatherConditions){
        binding.todayRecyclerView.apply {
            binding.todayRecyclerView.scrollToPosition(currentHour)
            binding.todayTitleDate.text = currentDate
            binding.weatherConditionForNow.text = getText(ForecastInfo.weatherConditionCode(meteo.hourly.weathercode[currentHour]))
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getCityName(sharedPreferencesHelper: SharedPreferencesHelper) {
        val cityName = sharedPreferencesHelper.getCityName()
        val regionName= sharedPreferencesHelper.getCountry()
        binding.todayTitleCity.text = "$cityName, $regionName"
    }
}

