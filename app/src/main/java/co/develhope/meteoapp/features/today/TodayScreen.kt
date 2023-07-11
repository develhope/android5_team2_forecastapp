package co.develhope.meteoapp.features.today

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
import co.develhope.meteoapp.databinding.ScreenTodayBinding
import co.develhope.meteoapp.features.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.ForecastInfo
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.network.DateUtils
import java.time.LocalDate
import java.time.LocalTime

@Suppress("IMPLICIT_CAST_TO_ANY", "UNUSED_EXPRESSION")
class TodayScreen : Fragment() {

    private lateinit var binding: ScreenTodayBinding

    private val viewModel: TodayViewModel by viewModels()

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

        val sharedPreferencesHelper = SharedPreferencesHelper(
            requireContext().getSharedPreferences(
                "MyPrefs",
                Context.MODE_PRIVATE
            )
        )

        viewModel.downloadForecastInfo(sharedPreferencesHelper)

        viewModel.forecastLiveData.observe(viewLifecycleOwner){
            showTodayMeteo(it)
        }

        getCityName(sharedPreferencesHelper)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showTodayMeteo(meteo: ForecastResult){
        binding.todayTomorrowRecyclerView.apply {
            binding.todayTomorrowRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.todayTomorrowRecyclerView.adapter = TodayTomorrowAdapter(meteo)
            binding.todayTomorrowRecyclerView.scrollToPosition(currentHour)
            binding.todayTitleDate.text = currentDate
            binding.weatherConditionForNow.text = getText(ForecastInfo.weatherConditionInfo(meteo.hourly.weathercode[currentHour]))
        }
    }

    private fun getCityName(sharedPreferencesHelper:SharedPreferencesHelper){
        val nullCity = getString(R.string.null_city)
        val cityName = when(sharedPreferencesHelper.getCityName()){
            null -> nullCity
            else -> sharedPreferencesHelper.getCityName()
        }
        val regionName = when(sharedPreferencesHelper.getCountry()){
            null -> " "
            else -> sharedPreferencesHelper.getCountry()
        }

        binding.todayTitleCity.setText("$cityName, $regionName")
    }
}

