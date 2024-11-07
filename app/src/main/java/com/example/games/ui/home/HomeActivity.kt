package com.example.games.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.databinding.ActivityMainBinding
import com.example.games.model.Game

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpRecyclerView(games: List<Game>) {
        binding.rvGames.adapter = GameAdapter(games, layoutInflater = layoutInflater)
        binding.rvGames.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpView() {
        homeViewModel.loadUserInfo(
            onLoading = {
                changeVisibilityInfoState(true)
            },
            onSuccess = { person ->
                binding.nameTextView.text = person.name
                binding.phoneTextView.text = person.phone
                binding.hobbyTextView.text = person.hobby
                binding.emailTextView.text = person.email
                changeVisibilityInfoState(false)
            },
            onFailure = { errorMessage ->
                Toast.makeText(
                    this@HomeActivity,
                    getString(errorMessage),
                    Toast.LENGTH_SHORT
                ).show()
                changeVisibilityInfoState(false)
            }
        )
        homeViewModel.loadListGames(
            onLoading = {
                changeVisibilityListState(true)
            },
            onSuccess = { games ->
                setUpRecyclerView(games)
                changeVisibilityListState(false)
            },
            onFailure = {
                Toast.makeText(
                    this@HomeActivity,
                    getString(it),
                    Toast.LENGTH_SHORT
                ).show()
                changeVisibilityListState(false)
            }
        )
    }

    private fun changeVisibilityInfoState(state: Boolean){
        binding.pbLoadInfoPerson.isVisible = state
        binding.nameTextView.isVisible = !state
        binding.phoneTextView.isVisible = !state
        binding.hobbyTextView.isVisible = !state
        binding.emailTextView.isVisible = !state
    }

    private fun changeVisibilityListState(state: Boolean){
        binding.pbLoadGames.isVisible = state
        binding.rvGames.isVisible = !state
    }
}