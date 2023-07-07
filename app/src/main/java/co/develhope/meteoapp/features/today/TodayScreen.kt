package co.develhope.meteoapp.features.today

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.ScreenTodayBinding
import co.develhope.meteoapp.features.data.ForecastResult
import co.develhope.meteoapp.features.network.DateUtils
import java.time.LocalDate
import java.util.Calendar
import java.util.Locale


class TodayScreen : Fragment() {

    private lateinit var binding: ScreenTodayBinding

    private val viewModel: TodayViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    private val currentDate = DateUtils.getDateForTodayAndTomorrowScreen("${LocalDate.now()}")


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

        viewModel.downloadForecastInfo()

        viewModel.forecastLiveData.observe(viewLifecycleOwner){
            showTodayMeteo(it)
        }

        binding.todayTitleDate.text = currentDate


    }

    private fun showTodayMeteo(meteo: ForecastResult){
        binding.todayTomorrowRecyclerView.apply {
            binding.todayTomorrowRecyclerView.layoutManager = LinearLayoutManager(context)
            binding.todayTomorrowRecyclerView.adapter = TodayTomorrowAdapter(meteo)
        }
    }
}

