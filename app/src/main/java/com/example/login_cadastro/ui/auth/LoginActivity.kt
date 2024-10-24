package com.example.login_cadastro.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.login_cadastro.ui.home.MainActivity
import com.example.login_cadastro.R
import com.example.login_cadastro.databinding.ActivityLoginBinding
import com.example.login_cadastro.model.Person
import com.example.login_cadastro.util.Sessao

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

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
    }


    private fun setUpView() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val pwd = binding.edtPwd.text.toString()
            var exist = false
            var peopleVerify: Person? = null
            Sessao.people.forEach { people ->
               if (people.user.email == email) {
                   peopleVerify = people
                   exist = true
               }
            }
            if (exist) {
                if (peopleVerify?.user?.pwd == pwd) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    Sessao.loged = peopleVerify
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@LoginActivity, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
            }
        }

        binding.singUp.setOnClickListener {
            val intent = Intent(this@LoginActivity, SingUpAtivity::class.java)
            startActivity(intent)
        }
    }
}