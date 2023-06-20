package co.develhope.meteoapp.features.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.ScreenSearchBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


data class Geo(val results : List<City>)
data class City(val country :String,val timezone: String, val name: String?)
interface GeoCoding {
    @GET("v1/search")
    suspend fun getGeoLocalization(@Query("name") name: String?): Geo
}

class SearchScreen : Fragment() {
    private lateinit var binding: ScreenSearchBinding
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://geocoding-api.open-meteo.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val geoCoding: GeoCoding = retrofit.create(GeoCoding::class.java)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = ScreenSearchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchWidget.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        val city = geoCoding.getGeoLocalization(query)
                        adapter(city)
                        Log.d("SearchScreen", "${city.results[0]}, $query")
                    }
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Log.d("SearchScreen"," $p0")
                return true
            }
        })
    }
    private fun adapter(city: Geo){
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerview.adapter = SearchScreenAdapter(city,city.results)
    }
}