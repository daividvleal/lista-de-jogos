package com.example.games.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.games.ui.home.HomeActivity
import com.example.games.R
import com.example.games.databinding.ActivityLoginBinding
import com.example.games.domain.model.House
import com.example.games.ui.home.HomeViewModel
import com.example.games.util.HOUSE
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var auth: FirebaseAuth? = null
    private val viewModel = AuthViewModel()
    private val homeViewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val house = intent.getParcelableExtra<House>(HOUSE)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView(house)
        auth = FirebaseAuth.getInstance()
    }


    private fun setUpView(house: House?) {
        //
        binding.houseItem.founder.text = house?.founder
        binding.houseItem.pet.text = house?.animal
        binding.houseItem.element.text = house?.element
        binding.houseItem.ghost.text = house?.ghost

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pwd = binding.edtPwd.text.toString()
            viewModel.login(
                email,
                pwd,
                onSuccess = {
                    homeViewModel.loadUserInfo(
                        onLoading = { },
                        onSuccess = { person ->
                            if (person.house?.id == house?.id) {
                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "Você não é bem vindo na ${house?.name}!", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onFailure = { errorMessage ->
                            Toast.makeText(this@LoginActivity, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                        }
                    )
                },
                onFailure = {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                }
            )
        }

        binding.singUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SingUpAtivity::class.java)
            intent.putExtra(HOUSE, house)
            startActivity(intent)
        }
    }
}