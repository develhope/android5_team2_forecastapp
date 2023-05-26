package co.develhope.meteoapp.features.today

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.ItemTodayBinding

class TodayAdapter(private val item: List<TodayWeatherCondition>) : RecyclerView.Adapter<TodayAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:ItemTodayBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: TodayWeatherCondition){
            binding.hourItem.setText(item.hour)
            binding.weatherImageItem.setImageResource(item.imageWeather)
            binding.gradeItem.text = item.grade.toString()
            binding.humidityPercentageItem.text = item.humidityPercentage.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodayBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(item[position])

    }

    override fun getItemCount(): Int {
        return item.size
    }
}