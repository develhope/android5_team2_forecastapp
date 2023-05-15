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
        binding = FragmentHomeScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val items = listOf(
            HomeScreenItemData(R.drawable.wc_sun_cloud,R.string.today,12,7,48,8),
            HomeScreenItemData(R.drawable.wc_sun,R.string.tomorrow,145,7,48,8),
            HomeScreenItemData(R.drawable.wc_sun,R.string.monday,2,15,37,5),
            HomeScreenItemData(R.drawable.wc_sun_cloud,R.string.tuesday,37,48,51,175),
            HomeScreenItemData(R.drawable.wc_rain_cloud,R.string.wednesday,189,34,85,150)
        )
        binding.recyclerView.adapter = ListAdapter(items)
    }
}