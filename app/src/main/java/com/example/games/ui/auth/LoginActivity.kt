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
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var auth: FirebaseAuth? = null
    private val viewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        auth = FirebaseAuth.getInstance()
    }


    private fun setUpView() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pwd = binding.edtPwd.text.toString()
            viewModel.login(
                email,
                pwd,
                onSuccess = {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                onFailure = {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                }
            )
        }

        binding.singUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SingUpAtivity::class.java)
            startActivity(intent)
        }
    }
}