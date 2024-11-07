package com.example.games.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.games.R
import com.example.games.databinding.ActivitySingUpAtivityBinding
import com.example.games.model.Person
import com.example.games.ui.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SingUpAtivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpAtivityBinding
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null
    private val viewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySingUpAtivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpView()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }


    private fun setUpView() {
        binding.registerButton.setOnClickListener {
            validateFieldsAndRegister()
        }
    }

    private fun validateFieldsAndRegister() {
        val name = binding.nameEdittext.text.toString()
        val phone = binding.phoneEdittext.text.toString()
        val hobby = binding.hobbyEdittext.text.toString()
        val email = binding.emailEdittext.text.toString()
        val pwd = binding.passwordEdittext.text.toString()
        val confirmPwd = binding.confirmPasswordEdittext.text.toString()

        val person = Person(name, phone, hobby, email)

        viewModel.createUser(
            person,
            pwd,
            confirmPwd,
            onSuccess = {
                goToMainActivity()
            },
            onFailure = { errorMessage ->
                Toast.makeText(
                    this@SingUpAtivity,
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}