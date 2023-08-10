package co.develhope.meteoapp.features.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.SearchScreenItemBinding
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.City
import co.develhope.meteoapp.features.data.remote.models.SearchCityResult
interface OnItemClickListener {
    fun onItemClick(itemData: City)
}
class SearchScreenAdapter( private val items: SearchCityResult, private val cities: List<City>, private val sharedPreferencesHelper: SharedPreferencesHelper,private val itemClickListener: OnItemClickListener? = null) : RecyclerView.Adapter<SearchScreenAdapter.ViewHolder>() {
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
//                Toast.makeText(binding.searchItem.context , "Retrieving the weather forecast for ${item.name}, ${item.admin1}", Toast.LENGTH_SHORT).show()
                sharedPreferencesHelper.saveLatitude(item.latitude)
                sharedPreferencesHelper.saveLongitude(item.longitude)
                sharedPreferencesHelper.saveCityName(item.name)
                sharedPreferencesHelper.saveCountry(item.admin1)
                sharedPreferencesHelper.saveClickedCity(item)
                itemClickListener?.onItemClick(item)
            }
        }
    }
}