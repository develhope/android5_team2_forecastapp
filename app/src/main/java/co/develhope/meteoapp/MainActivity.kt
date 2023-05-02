package co.develhope.meteoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import co.develhope.meteoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener{
            val navController = binding.navHostFragment.findNavController()
            when(it.itemId){
                R.id.home_button -> navController.navigate(R.id.fragment_home)
                R.id.today_button -> navController.navigate(R.id.fragment_today)
                R.id.tomorrow_button -> navController.navigate(R.id.fragment_tomorrow)
                R.id.search_button -> navController.navigate(R.id.fragment_search)
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }
}

