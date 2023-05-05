package co.develhope.meteoapp.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import co.develhope.meteoapp.R
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
                R.id.home_screen -> navController.navigate(R.id.home_screen)
                R.id.today_screen -> navController.navigate(R.id.today_screen)
                R.id.tomorrow_screen -> navController.navigate(R.id.tomorrow_screen)
                R.id.search_screen -> navController.navigate(R.id.search_screen)
                else -> return@setOnItemSelectedListener false
            }
            return@setOnItemSelectedListener true
        }
    }
}

