package co.develhope.meteoapp.features.ui.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ItemTodayBinding
import co.develhope.meteoapp.features.data.remote.ForecastInfo
import co.develhope.meteoapp.features.data.remote.models.WeatherConditions

class TodayAdapter(private val item: WeatherConditions) :
    RecyclerView.Adapter<TodayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemTodayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 24

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(item, position)
        holder.visibilityCard()
    }

    inner class ViewHolder(private val binding : ItemTodayBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: WeatherConditions, position: Int){
            val hour = item.hourly.time[position]
            val temperature = item.hourly.temperature[position].toInt()
            val precipitationProbability = item.hourly.precipitationProbability[position].toInt()
            val apparentTemperature = item.hourly.apparentTemperature[position].toInt()
            val uvIndex = item.hourly.uvIndex[position]
            val humidity = item.hourly.relativeHumidity[position]
            val wind = item.hourly.windspeed10m[position]
            val coverage = item.hourly.cloudcover[position]
            val rainInMm = item.hourly.rain[position].toInt()
            binding.hourItem.text = hour.takeLast(5)
            binding.gradeItem.text = temperature.toString().plus(item.hourlyHunits.temperatureIUnit)
            binding.rainProbabilityItem.text = precipitationProbability.toString().plus(item.hourlyHunits.humidityUnit)
            binding.cardPerceivedGradeVal.text = apparentTemperature.toString().plus(item.hourlyHunits.temperatureIUnit)
            binding.cardUvVal.text = uvIndex.toString()
            binding.cardHumidityVal.text = humidity.toString().plus(item.hourlyHunits.humidityUnit)
            binding.cardWindVal.text = wind.toString().plus(item.hourlyHunits.windSpeedUnit)
            binding.cardCoverageVal.text = coverage.toString().plus(item.hourlyHunits.cloudcover)
            binding.cardRainVal.text = rainInMm.toString().plus(item.hourlyHunits.rain)
            binding.cardWindDirectionVal.setText(ForecastInfo.windDirection(item.hourly.windDirection[position]))
            
            when(item.hourly.isDay[position]){
                0 -> binding.weatherImageItem.setImageResource(R.drawable.moon)
                1 -> when(item.hourly.weathercode[position]){
                    0 -> binding.weatherImageItem.setImageResource(R.drawable.wc_sun)
                    in 1..3 -> binding.weatherImageItem.setImageResource(R.drawable.wc_sun_cloud)
                    else ->  binding.weatherImageItem.setImageResource(R.drawable.wc_rain_cloud)
                }
            }
        }

        fun visibilityCard(){
            binding.info.setOnClickListener {
                if (binding.cardView.visibility == View.GONE) {
                    binding.cardView.visibility = View.VISIBLE
                    binding.arrowCardView.rotation = 360F
                    binding.vector.visibility = View.GONE
                } else {
                    binding.cardView.visibility = View.GONE
                    binding.arrowCardView.rotation = 180F
                    binding.vector.visibility = View.VISIBLE
                }
            }
        }
    }
}