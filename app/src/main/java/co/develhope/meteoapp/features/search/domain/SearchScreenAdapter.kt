package co.develhope.meteoapp.features.search.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.SearchScreenItemBinding



class SearchScreenAdapter(private val items: SearchCityResult, private val cities: List<City>) : RecyclerView.Adapter<SearchScreenAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SearchScreenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(items, position)
        holder.clicking(items.results[position])
    }

    inner class ViewHolder (private val binding : SearchScreenItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun binding(item: SearchCityResult, position: Int){
            val name = item.results[position].name
            val country = item.results[position].country
            val timezone = item.results[position].admin1
            binding.searchItemCityName.text = name.toString()
            binding.searchItemWeatherConditions.text = timezone
            binding.searchScreenTemperature.text = country
        }
        fun clicking(item: City){
            binding.searchItem.setOnClickListener {
                Toast.makeText(binding.searchItem.context , "latitude:${item.latitude} longitude:${item.longitude}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}