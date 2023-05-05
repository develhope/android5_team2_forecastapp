package co.develhope.meteoapp.features.tomorrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.meteoapp.databinding.FragmentTodayBinding
import co.develhope.meteoapp.databinding.FragmentTomorrowBinding


class Tomorrow : Fragment() {
    private lateinit var binding: FragmentTomorrowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTomorrowBinding.inflate(inflater)
        return binding.root
    }
}