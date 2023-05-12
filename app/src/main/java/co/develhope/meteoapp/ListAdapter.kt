package co.develhope.meteoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.develhope.meteoapp.databinding.HomePageItemBinding



class ListAdapter(private val items:List<ItemData>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = HomePageItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.size



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding(items[position])
    }

    inner class ViewHolder(private val binding: HomePageItemBinding) : RecyclerView.ViewHolder(binding.root){
       fun binding (item:ItemData){
           binding.day.setText(item.day)
           binding.wcIcon.setImageResource(item.weather)
           binding.wind.text = item.wind.toString()
           binding.tempMin.text = item.temp_min.toString()
           binding.tempMax.text = item.temp_max.toString()
           binding.mm.text = item.mm_rain.toString()
       }
    }
}