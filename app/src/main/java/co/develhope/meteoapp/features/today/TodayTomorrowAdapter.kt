package co.develhope.meteoapp.features.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.contains
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.ItemTodayTomorrowBinding

class TodayTomorrowAdapter(private val item: List<TodayTomorrowWeatherCondition>) : RecyclerView.Adapter<TodayTomorrowAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:ItemTodayTomorrowBinding) : RecyclerView.ViewHolder(binding.root){

        fun bindItem(item: TodayTomorrowWeatherCondition){
            binding.hourItem.setText(item.hour)
            binding.weatherImageItem.setImageResource(item.imageWeather)
            binding.gradeItem.text = item.grade.toString()
            binding.humidityPercentageItem.text = item.humidityPercentage.toString()
            binding.cardPerceivedGradeVal.text = item.gradeInCardView
            binding.cardUvVal.text = item.uvIndexInCardView
            binding.cardHumidityVal.text = item.humidityInCardView
            binding.cardWindVal.text = item.WindInCardView
            binding.cardCoverageVal.text = item.coverPercentageInCardView
            binding.cardRainVal.text = item.rainInCmInCardView
        }

        fun visibilityCard(){
            binding.info.setOnClickListener {
                if (binding.cardView.visibility == View.GONE) {
                    binding.cardView.visibility = View.VISIBLE
                } else {
                    binding.cardView.visibility = View.GONE
                    binding.arrowCardView.rotation = 180F
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTodayTomorrowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(item[position])
        holder.visibilityCard()
    }

    override fun getItemCount(): Int {
        return item.size
    }
}