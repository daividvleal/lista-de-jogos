package com.example.games.ui.houses

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.databinding.ActivityHousesBinding
import com.example.games.domain.model.House
import com.example.games.ui.auth.LoginActivity
import com.example.games.util.HOUSE

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

    private fun setUpRecyclerView(houses: List<House>) {
        binding.rvHouses.adapter = HousesAdapter(houses) {
            val intent = Intent(this@HousesActivity, LoginActivity::class.java)
            intent.putExtra(HOUSE, it)
            startActivity(intent)
        }
        binding.rvHouses.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpLoading() {
        binding.pbHouses.isVisible = true
        binding.rvHouses.isVisible = false
        binding.txtErrorMessage.isVisible = false
    }

    private fun setUpSuccessState() {
        binding.pbHouses.isVisible = false
        binding.rvHouses.isVisible = true
        binding.txtErrorMessage.isVisible = false
    }

    private fun setUpErrorState() {
        binding.pbHouses.isVisible = false
        binding.rvHouses.isVisible = false
        binding.txtErrorMessage.isVisible = true
    }

    private fun setUpObservers() {
        viewModel.houses.observe(this) { houses ->
            setUpSuccessState()
            setUpRecyclerView(houses)
        }
        viewModel.errorLoadingHouses.observe(this) {
            setUpErrorState()
        }
    }

    override fun onResume() {
        setUpLoading()
        viewModel.loadHouses()
        super.onResume()
    }
}