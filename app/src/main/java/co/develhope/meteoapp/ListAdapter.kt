package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.HomeScreenItemBinding

class ListAdapter(private val items:List<WeatherConditions>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = HomeScreenItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding(items[position])
    }

    inner class ViewHolder(private val binding: HomeScreenItemBinding) : RecyclerView.ViewHolder(binding.root){
       fun binding (item:WeatherConditions){
           binding.currentDay.setText(item.day)
           binding.wcIcon.setImageResource(item.weather)
           binding.windValue.text = item.wind.toString()
           binding.minTemperatureValue.text = item.temp_min.toString()
           binding.maxTemperatureValue.text = item.temp_max.toString()
           binding.rainfallValue.text = item.mm_rain.toString()
       }
    }
}