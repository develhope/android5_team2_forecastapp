package co.develhope.meteoapp.features.ui.tomorrow

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
import co.develhope.meteoapp.databinding.ScreenTomorrowBinding
import co.develhope.meteoapp.features.data.local.DateUtils
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import org.koin.android.ext.android.inject
import java.time.LocalDate

class TomorrowScreen : Fragment() {

    private lateinit var binding: ScreenTomorrowBinding

    private val viewModel: TomorrowViewModel by inject()
    private val sharedPreferencesHelper: SharedPreferencesHelper by inject()

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


        if (sharedPreferencesHelper.getCityName().isNullOrEmpty()){
            findNavController().navigate(R.id.action_tomorrow_screen_to_search_screen)
            Toast.makeText(context,"Search a city!", Toast.LENGTH_SHORT).show()
        }

        viewModel.downloadForecastInfo(sharedPreferencesHelper)

        viewModel.forecastLiveData.observe(viewLifecycleOwner){
            showTomorrowMeteo(it)
        }
        getCityName(sharedPreferencesHelper)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showTomorrowMeteo(meteo: WeatherConditions){
        binding.tomorrowRecyclerView.apply {
            binding.tomorrowRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.tomorrowRecyclerView.adapter = TomorrowAdapter(meteo)
            binding.tomorrowTitleDate.text = tomorrowDate
        }
    }

    private fun getCityName(sharedPreferencesHelper: SharedPreferencesHelper){
        binding.todayTitleCity.text = "${sharedPreferencesHelper.getCityName()}, ${sharedPreferencesHelper.getCountry()}"
    }
}


