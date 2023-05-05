package co.develhope.meteoapp.features.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.meteoapp.databinding.ScreenSearchBinding

class SearchScreen : Fragment() {
    private lateinit var binding: ScreenSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ScreenSearchBinding.inflate(inflater)
        return binding.root
    }

}