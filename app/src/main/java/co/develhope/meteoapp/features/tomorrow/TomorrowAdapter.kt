package co.develhope.meteoapp.features.tomorrow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.ItemTomorrowBinding

class TomorrowAdapter(private val item: List<TomorrowWeatherCondition>) : RecyclerView.Adapter<TomorrowAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTomorrowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: TomorrowWeatherCondition){
            binding.hourItem.setText(item.hour)
            binding.weatherImageItem.setImageResource(item.imageWeather)
            binding.gradeItem.text = item.grade.toString()
            binding.humidityPercentageItem.text = item.humidityPercentage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTomorrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }
}