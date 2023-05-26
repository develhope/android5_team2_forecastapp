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
        binding.todayRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TodayAdapter(createWeatherList())
        }
    }

    private fun createWeatherList(): List<TodayWeatherCondition>{
        return listOf(
            TodayWeatherCondition(R.string.hour0,R.drawable.moon,12,0),
            TodayWeatherCondition(R.string.hour1,R.drawable.moon,12,0),
            TodayWeatherCondition(R.string.hour2,R.drawable.moon,12,0),
            TodayWeatherCondition(R.string.hour3,R.drawable.moon,13,0),
            TodayWeatherCondition(R.string.hour4,R.drawable.moon,14,0),
            TodayWeatherCondition(R.string.hour5,R.drawable.moon,15,0),
            TodayWeatherCondition(R.string.hour6,R.drawable.sun,16,0),
            TodayWeatherCondition(R.string.hour7,R.drawable.foggy,18,0),
            TodayWeatherCondition(R.string.hour8,R.drawable.foggy,20,0),
            TodayWeatherCondition(R.string.hour9,R.drawable.rainy,22,0),
            TodayWeatherCondition(R.string.hour10,R.drawable.foggy,25,0),
            TodayWeatherCondition(R.string.hour12,R.drawable.foggy,26,0),
            TodayWeatherCondition(R.string.hour1pm,R.drawable.sun,28,0),
            TodayWeatherCondition(R.string.hour2pm,R.drawable.sun,30,0),
            TodayWeatherCondition(R.string.hour3pm,R.drawable.sun,29,0),
            TodayWeatherCondition(R.string.hour4pm,R.drawable.sun,28,0),
            TodayWeatherCondition(R.string.hour5pm,R.drawable.sun,27,0),
            TodayWeatherCondition(R.string.hour6pm,R.drawable.foggy,24,0),
            TodayWeatherCondition(R.string.hour7pm,R.drawable.foggy,20,0),
            TodayWeatherCondition(R.string.hour8pm,R.drawable.foggy,20,0),
            TodayWeatherCondition(R.string.hour9pm,R.drawable.sun,21,0),
            TodayWeatherCondition(R.string.hour10pm,R.drawable.moon,20,0),
            TodayWeatherCondition(R.string.hour11pm,R.drawable.moon,20,0),
        )
    }

}

