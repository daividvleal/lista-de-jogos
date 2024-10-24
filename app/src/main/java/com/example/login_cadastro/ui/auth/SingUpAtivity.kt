package com.example.login_cadastro.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login_cadastro.ui.home.MainActivity
import com.example.login_cadastro.R
import com.example.login_cadastro.databinding.ActivitySingUpAtivityBinding
import com.example.login_cadastro.model.Person
import com.example.login_cadastro.model.User
import com.example.login_cadastro.util.Sessao

class SingUpAtivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpAtivityBinding

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
    }


    private fun setUpView(){
        binding.registerButton.setOnClickListener {
            validateFieldsAndRegister()
        }
    }

    private fun validateFieldsAndRegister(){
        val name = binding.nameEdittext.text.toString()
        val phone = binding.phoneEdittext.text.toString()
        val hobby = binding.hobbyEdittext.text.toString()
        val email = binding.emailEdittext.text.toString()
        val pwd = binding.passwordEdittext.text.toString()
        val confirmPwd = binding.confirmPasswordEdittext.text.toString()

        if (email.isBlank()) {
            binding.emailEdittext.error = "Email is required"
            return
        }

        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (!emailRegex.matches(email)) {
            binding.emailEdittext.error = "Invalid email format"
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailEdittext.error = "Invalid email format"
            return
        }

        if (pwd.isBlank()) {
            binding.passwordEdittext.error = "Password is required"
            return
        }

        if (pwd.length < 6) {
            binding.passwordEdittext.error = "Password must be at least 6 characters"
            return
        }

        if (confirmPwd.isBlank()) {
            binding.confirmPasswordEdittext.error = "Confirm password is required"
            return
        }

        if (name.isBlank()) {
            binding.nameEdittext.error = "Name is required"
            return
        }

        if (phone.isBlank()) {
            binding.phoneEdittext.error = "Phone is required"
            return
        }

        if (!android.util.Patterns.PHONE.matcher(phone).matches()) {
            binding.phoneEdittext.error = "Invalid phone number"
            return
        }

        if (hobby.isBlank()) {
            binding.hobbyEdittext.error = "Hobby is required"
            return
        }



        if (pwd != confirmPwd) {
            binding.confirmPasswordEdittext.error = "Passwords do not match"
            return
        }

        val person = Person(User(email, pwd), name, phone, hobby)
        Sessao.people.add(person)
        Sessao.loged = person
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}