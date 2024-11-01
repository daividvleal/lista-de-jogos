package com.example.games.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.games.databinding.ActivityMainBinding
import com.example.games.model.Person
import com.example.games.util.Sessao
import com.example.games.util.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var auth: FirebaseAuth? = null
    private var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        setUpView()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvGames.adapter = GameAdapter(Sessao.games, layoutInflater = layoutInflater)
        binding.rvGames.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpView() {
        val id =  auth?.currentUser?.uid!!
        firestore?.collection(USERS)?.document(id)?.get()
            ?.addOnFailureListener {
            Toast.makeText(
                this@MainActivity,
                "Falha ao carregar as informações do usuário!",
                Toast.LENGTH_SHORT
            ).show()
        }?.addOnSuccessListener {
            val person = it.toObject(Person::class.java)
            binding.nameTextView.text = person?.name
            binding.phoneTextView.text = person?.phone
            binding.hobbyTextView.text = person?.hobby
            binding.emailTextView.text = person?.email
        }
    }
}