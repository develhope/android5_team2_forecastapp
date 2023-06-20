package co.develhope.meteoapp.features.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.ScreenSearchBinding
import co.develhope.meteoapp.databinding.SearchScreenItemBinding


class SearchScreenAdapter(private val items: Geo, private val cities: List<City>) : RecyclerView.Adapter<SearchScreenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itembinding = SearchScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itembinding)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items, position)
    }

    inner class ViewHolder (private val binding : SearchScreenItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun binding(item: Geo, position: Int){
            val name = item.results[position].name
            val country = item.results[position].country
            val timezone = item.results[position].timezone
            binding.searchItemCityName.text = name.toString()
            binding.searchItemWeatherConditions.text = country
            binding.searchScreenTemperature.text = timezone
        }
    }
}