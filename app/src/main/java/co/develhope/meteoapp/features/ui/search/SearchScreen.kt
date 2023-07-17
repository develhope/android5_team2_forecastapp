package co.develhope.meteoapp.features.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.R
import co.develhope.meteoapp.databinding.ScreenSearchBinding
import co.develhope.meteoapp.features.data.local.SharedPreferencesHelper
import co.develhope.meteoapp.features.data.remote.models.SearchCityResult

class SearchScreen : Fragment() {
    private lateinit var binding: ScreenSearchBinding
    private val viewModel: SearchCityViewModel by viewModels()

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
//        dichiara shared preferences Helper per utilizzarlo
        val sharedPreferencesHelper = SharedPreferencesHelper(
            requireContext().getSharedPreferences(
                "MyPrefs",
                Context.MODE_PRIVATE
            )
        )
        viewModel.searchCitiesLiveData.observe(viewLifecycleOwner) {
            showCities(it, sharedPreferencesHelper)
        }
        binding.searchWidget.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length!! >= 4) {
                    viewModel.getCities(query)
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
                    emptyTheList(sharedPreferencesHelper)
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
            binding.searchRecyclerview.layoutManager = LinearLayoutManager(context)
            binding.searchRecyclerview.adapter =
                SearchScreenAdapter(city, city.results, sharedPreferencesHelper)
        } else {
            Toast.makeText(
                context,
                getString(R.string.error_search_invalid_city),
                Toast.LENGTH_SHORT
            ).show()
            emptyTheList(sharedPreferencesHelper)
        }
    }

    private fun emptyTheList(sharedPreferencesHelper: SharedPreferencesHelper) {
        binding.searchRecyclerview.adapter =
            SearchScreenAdapter(SearchCityResult(emptyList()), emptyList(), sharedPreferencesHelper)
    }

}