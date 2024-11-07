package com.example.games.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.databinding.ActivityMainBinding
import com.example.games.util.Sessao

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvGames.adapter = GameAdapter(Sessao.games, layoutInflater = layoutInflater)
        binding.rvGames.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpView() {
        homeViewModel.loadUserInfo(
            onSuccess = { person ->
                binding.nameTextView.text = person.name
                binding.phoneTextView.text = person.phone
                binding.hobbyTextView.text = person.hobby
                binding.emailTextView.text = person.email
            },
            onFailure = { errorMessage ->
                Toast.makeText(
                    this@HomeActivity,
                    getString(errorMessage),
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}