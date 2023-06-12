package co.develhope.meteoapp.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.features.home.domain.ListAdapter
import co.develhope.meteoapp.databinding.ScreenHomeBinding
import co.develhope.meteoapp.features.home.domain.WeatherConditions
import co.develhope.meteoapp.features.network.HomeViewModel

class HomeScreen : Fragment() {
    private lateinit var binding: ScreenHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.retrieveMeteo()
        viewModel.weeklyWeatherLiveData.observe(viewLifecycleOwner){
            showMeteo(it)
        }
    }
    fun showMeteo(meteo: WeatherConditions){
        binding.recyclerView.adapter = ListAdapter(meteo)
        binding.recyclerView.layoutManager =LinearLayoutManager(context)
    }

}
