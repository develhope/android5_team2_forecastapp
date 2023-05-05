package co.develhope.meteoapp.features.today

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.develhope.meteoapp.databinding.FragmentHomeBinding
import co.develhope.meteoapp.databinding.FragmentTodayBinding

class Today : Fragment() {

    private lateinit var binding: FragmentTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTodayBinding.inflate(inflater)
        return binding.root
    }

}
