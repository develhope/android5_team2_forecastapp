package co.develhope.meteoapp.features.tomorrow

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
import co.develhope.meteoapp.databinding.ScreenTomorrowBinding
import co.develhope.meteoapp.features.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.network.DateUtils
import co.develhope.meteoapp.features.today.TodayTomorrowAdapter
import co.develhope.meteoapp.features.today.TodayTomorrowWeatherCondition
import co.develhope.meteoapp.features.today.TodayViewModel
import java.time.LocalDate

class TomorrowScreen : Fragment() {

    private lateinit var binding: ScreenTomorrowBinding

    private val viewModel: TomorrowViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private val tomorrowDate = DateUtils.getDateForTodayAndTomorrowScreen("${LocalDate.now().plusDays(1)}")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenTomorrowBinding.inflate(inflater)
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
            showTomorrowMeteo(it)
        }

        getCityName(sharedPreferencesHelper)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showTomorrowMeteo(meteo: ForecastResult){
        binding.todayTomorrowRecyclerView.apply {
            binding.todayTomorrowRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.todayTomorrowRecyclerView.adapter = TodayTomorrowAdapter(meteo)
            binding.tomorrowTitleDate.text = tomorrowDate
        }
    }

    private fun getCityName(sharedPreferencesHelper:SharedPreferencesHelper){
        binding.todayTitleCity.text = "${sharedPreferencesHelper.getCityName()}, ${sharedPreferencesHelper.getCountry()}"
    }
}


