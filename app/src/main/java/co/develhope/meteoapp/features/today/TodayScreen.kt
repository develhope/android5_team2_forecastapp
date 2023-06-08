package co.develhope.meteoapp.features.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenTodayBinding


class TodayScreen : Fragment() {

    private lateinit var binding: ScreenTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ScreenTodayBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpLinearView()
    }

    private fun setUpLinearView(){
        binding.todayTomorrowRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TodayTomorrowAdapter(createWeatherList())
        }
    }

    private fun createWeatherList(): List<TodayTomorrowWeatherCondition>{
        return listOf(
            TodayTomorrowWeatherCondition(R.string.hour0,R.drawable.moon,12,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour1,R.drawable.moon,12,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour2,R.drawable.moon,12,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour3,R.drawable.moon,13,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour4,R.drawable.moon,14,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour5,R.drawable.moon,15,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour6,R.drawable.sun,16,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour7,R.drawable.foggy,18,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour8,R.drawable.foggy,20,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour9,R.drawable.rainy,22,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour10,R.drawable.foggy,25,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour12,R.drawable.foggy,26,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour1pm,R.drawable.sun,28,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour2pm,R.drawable.sun,30,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour3pm,R.drawable.sun,29,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour4pm,R.drawable.sun,28,0,"grade","Uv","Humidity","wind","cover","rain"),
            TodayTomorrowWeatherCondition(R.string.hour5pm,R.drawable.sun,27,0,"grade","Uv","Humidity","wind","cover","rain"),
        )
    }
}

