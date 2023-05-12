package co.develhope.meteoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import co.develhope.meteoapp.databinding.FragmentHomeScreenBinding



class HomeScreen : Fragment() {
    private lateinit var binding: FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val items = listOf(
            ItemData(R.drawable.wc_sun_cloud,R.string.oggi,12,7,48,8),
            ItemData(R.drawable.wc_sun,R.string.domani,145,7,48,8),
            ItemData(R.drawable.wc_sun,R.string.lunedì,2,15,37,5),
            ItemData(R.drawable.wc_sun_cloud,R.string.martedì,37,48,51,175),
            ItemData(R.drawable.wc_rain_cloud,R.string.mercoledì,189,34,85,150)
        )
        binding.recyclerView.adapter = ListAdapter(items)
    }
}