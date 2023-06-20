package co.develhope.meteoapp.features.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ItemTodayTomorrowBinding
import co.develhope.meteoapp.features.data.ForecastResult

class TodayTomorrowAdapter(private val item: ForecastResult) :
    RecyclerView.Adapter<TodayTomorrowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodayTomorrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(item, position)
        holder.visibilityCard()
    }

    override fun getItemCount(): Int = 24

    inner class ViewHolder(private val binding : ItemTodayTomorrowBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: ForecastResult, position: Int){
            val hour = item.hourly.time[position]
            val temperature = item.hourly.temperature[position].toInt()
            val generalHumidity = item.hourly.dewPoint[position].toInt()
            val apparentTemperature = item.hourly.apparentTemperature[position].toInt()
            val uvIndex = item.hourly.uvIndex[position]
            val humidity = item.hourly.relativeHumidity[position].toInt()
            val wind = item.hourly.windspeed10m[position]
            val coverage = item.hourly.cloudcover[position]
            val rainInMm = item.hourly.rain[position].toInt()
            binding.hourItem.text = hour.takeLast(5)
            binding.gradeItem.text = temperature.toString().plus(item.hourlyHunits.temperatureIUnit)
            binding.humidityPercentageItem.text = generalHumidity.toString().plus(item.hourlyHunits.humidityUnit)
            binding.cardPerceivedGradeVal.text = apparentTemperature.toString().plus(item.hourlyHunits.temperatureIUnit)
            binding.cardUvVal.text = uvIndex.toString()
            binding.cardHumidityVal.text = humidity.toString().plus(item.hourlyHunits.humidityUnit)
            binding.cardWindVal.text = wind.toString().plus(item.hourlyHunits.windSpeedUnit)
            binding.cardCoverageVal.text = coverage.toString().plus(item.hourlyHunits.cloudcover)
            binding.cardRainVal.text = rainInMm.toString().plus(item.hourlyHunits.rain)
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

                } else {
                    binding.cardView.visibility = View.GONE
                    binding.arrowCardView.rotation = 180F
                }
            }
        }
    }
}