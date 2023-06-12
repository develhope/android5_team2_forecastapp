package co.develhope.meteoapp.features.home.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.HomeScreenItemBinding

class ListAdapter(private val items: WeatherConditions) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

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
            val maxTemperature = item.daily.maxTemperature[position]
            val minTemperature = item.daily.minTemperature[position]
            val date = item.daily.time[position]
            val rain = item.daily.rainSum[position]
            val wind = item.daily.windMax[position]
            binding.maxTemperatureValue.text = maxTemperature.toString().plus("°")
            binding.minTemperatureValue.text = minTemperature.toString().plus("°")
            binding.dayAndMonth.text = date
            binding.rainfallValue.text = rain.toString().plus("mm")
            binding.windValue.text = wind.toString().plus("km/h")
//            TODO add correct weather images corresponding to each weathercode
            when(item.daily.weathercode[position]){
                0 -> binding.wcIcon.setImageResource(R.drawable.wc_sun)
                in 1..3 -> binding.wcIcon.setImageResource(R.drawable.wc_sun_cloud)
                else ->  binding.wcIcon.setImageResource(R.drawable.wc_rain_cloud)
            }
        }
    }

}