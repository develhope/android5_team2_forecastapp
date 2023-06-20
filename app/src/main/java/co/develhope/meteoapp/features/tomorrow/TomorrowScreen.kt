package co.develhope.meteoapp.features.tomorrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenTomorrowBinding
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.today.TodayTomorrowAdapter
import co.develhope.meteoapp.features.today.TodayTomorrowWeatherCondition
import co.develhope.meteoapp.features.today.TodayViewModel

class TomorrowScreen : Fragment() {

    private lateinit var binding: ScreenTomorrowBinding

    private val viewModel: TomorrowViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenTomorrowBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.downloadForecastInfo()

        viewModel.forecastLiveData.observe(viewLifecycleOwner){
            showTomorrowMeteo(it)
        }
    }

    private fun showTomorrowMeteo(meteo: ForecastResult){
        binding.todayTomorrowRecyclerView.apply {
            binding.todayTomorrowRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.todayTomorrowRecyclerView.adapter = TodayTomorrowAdapter(meteo)
        }
    }
}


