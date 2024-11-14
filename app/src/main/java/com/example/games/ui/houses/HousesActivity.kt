package com.example.games.ui.houses

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.games.databinding.ActivityHousesBinding

class HousesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHousesBinding
    private val viewModel = HousesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHousesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.houses.observe(this) { houses ->
            houses.forEach {
                Log.i("HOUSE TAG", it.name + " - " + it.founder)
            }
            binding.response.text =  houses.toString()
        }
        viewModel.errorLoadingHouses.observe(this) {
            Log.e("ERROR TAG", getString(it))
        }
    }

    override fun onResume() {
        viewModel.loadHouses()
        super.onResume()
    }
}