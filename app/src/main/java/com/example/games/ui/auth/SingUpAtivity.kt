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
import com.example.games.ui.home.MainActivity
import com.example.games.util.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SingUpAtivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingUpAtivityBinding
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null

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

        val person = Person(name, phone, hobby, email)

        auth?.createUserWithEmailAndPassword(
            person.email,
            pwd
        )?.addOnFailureListener {
            Toast.makeText(
                this@SingUpAtivity,
                "Falha na criação do seu usuário! Tente novamente por favor!",
                Toast.LENGTH_SHORT
            ).show()
        }?.addOnSuccessListener {
            val user = auth?.currentUser
            person.uId = user?.uid
            firestore?.collection(USERS)?.document(person.uId!!)?.set(person)?.addOnSuccessListener {
                goToMainActivity()
            }?.addOnFailureListener {
                Toast.makeText(
                    this@SingUpAtivity,
                    "Falha na criação do seu usuário! Tente novamente por favor!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}