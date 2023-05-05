package co.develhope.meteoapp.features.tomorrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.meteoapp.databinding.ScreenTomorrowBinding


class TomorrowScreen : Fragment() {
    private lateinit var binding: ScreenTomorrowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ScreenTomorrowBinding.inflate(inflater)
        return binding.root
    }
}