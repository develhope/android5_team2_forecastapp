package co.develhope.meteoapp.features.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.meteoapp.databinding.ScreenHomeBinding

class HomeScreen : Fragment() {

    private lateinit var binding: ScreenHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ScreenHomeBinding.inflate(inflater)
        return binding.root
    }

}
