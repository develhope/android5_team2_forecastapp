package co.develhope.meteoapp.features.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.HomeScreenItemBinding
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions
import co.develhope.meteoapp.features.data.local.DateUtils.getDayOfWeek
import co.develhope.meteoapp.features.data.local.DateUtils.getMonthAndDay

class HomeScreenAdapter(private val items: WeatherConditions) :
    RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            HomeScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items, position)
    }

    inner class ViewHolder(private val binding: HomeScreenItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(item: WeatherConditions, position: Int) {
            val usedPosition = position + 1
            val maxTemperature = item.daily.maxTemperature[usedPosition].toInt()
            val minTemperature = item.daily.minTemperature[usedPosition].toInt()
            val date = item.daily.time[usedPosition]
            val rain = item.daily.rainSum[usedPosition]
            val wind = item.daily.windMax[usedPosition]
            val monthAndDay = getMonthAndDay(date)
            val specificDay = getDayOfWeek(date)
            binding.currentDay.text = specificDay
            binding.maxTemperatureValue.text = maxTemperature.toString().plus("°")
            binding.minTemperatureValue.text = minTemperature.toString().plus("°")
            if (position == 0) {
                binding.currentDay.setText(R.string.tomorrow)
            }
            binding.dayAndMonth.text = monthAndDay
            binding.rainfallValue.text = rain.toString().plus("mm")
            binding.windValue.text = wind.toString().plus("km/h")
//            TODO add correct weather images corresponding to each weathercode
            when (item.daily.weathercode[usedPosition]) {
                0 -> binding.wcIcon.setImageResource(R.drawable.wc_sun)
                in 1..3 -> binding.wcIcon.setImageResource(R.drawable.wc_sun_cloud)
                else -> binding.wcIcon.setImageResource(R.drawable.wc_rain_cloud)
            }
        }
    }
}
