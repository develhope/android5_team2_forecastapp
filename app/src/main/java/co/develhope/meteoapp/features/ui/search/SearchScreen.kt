package co.develhope.meteoapp.features.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenSearchBinding
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.City
import co.develhope.meteoapp.features.data.remote.models.SearchCityResult
import org.koin.android.ext.android.inject

class SearchScreen : Fragment(), OnItemClickListener {
    private lateinit var binding: ScreenSearchBinding

    private val viewModel: SearchCityViewModel by inject()

    private val sharedPreferencesHelper: SharedPreferencesHelper by inject()

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
        showRecentlySearchedCities(sharedPreferencesHelper)
        viewModel.searchCitiesLiveData.observe(viewLifecycleOwner) {
            showCities(it, sharedPreferencesHelper)
        }

        binding.searchWidget.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! >= 4) {
                    binding.searchWidget.clearFocus()
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.error_search_too_short),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0.isNullOrEmpty() || p0.length <= 3) {
                    showRecentlySearchedCities(sharedPreferencesHelper)
                } else {
                    viewModel.getCity(p0.trim())
                }
                return true
            }
        })
    }

    private fun showCities(
        city: SearchCityResult,
        sharedPreferencesHelper: SharedPreferencesHelper
    ) {
        if (city.results != null) {
            binding.textviewScreenSearch.visibility = View.GONE
            binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.searchRecyclerview.adapter =
                SearchScreenAdapter(city, city.results, sharedPreferencesHelper, this)
        } else {
            Toast.makeText(
                context,
                getString(R.string.error_search_invalid_city),
                Toast.LENGTH_SHORT
            ).show()
            showRecentlySearchedCities(sharedPreferencesHelper)
        }

    }

    private fun showRecentlySearchedCities(sharedPreferencesHelper: SharedPreferencesHelper) {
        binding.textviewScreenSearch.visibility = View.VISIBLE
        binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.searchRecyclerview.adapter =
            SearchScreenAdapter(
                SearchCityResult(sharedPreferencesHelper.getRecentlySearchedCities().reversed()),
                sharedPreferencesHelper.getRecentlySearchedCities(),
                sharedPreferencesHelper,
                this
            )
    }

    override fun onItemClick(itemData: City) {
        findNavController().navigate(R.id.action_search_screen_to_home_screen)
    }
}