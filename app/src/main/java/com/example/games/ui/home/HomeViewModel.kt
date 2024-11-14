package com.example.games.ui.home

import androidx.lifecycle.ViewModel
import com.example.games.R
import com.example.games.domain.model.Game
import com.example.games.domain.model.Person
import com.example.games.util.GAMES
import com.example.games.util.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class HomeViewModel: ViewModel() {

    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    fun loadUserInfo(
        onLoading: () -> Unit,
        onSuccess: (Person) -> Unit,
        onFailure: (Int) -> Unit
    ){
        onLoading.invoke()
        firestore.collection(USERS).document(auth.currentUser?.uid!!).get()
            .addOnFailureListener {
                onFailure(R.string.load_user_info_error_message)
            }.addOnSuccessListener {
                val person = it.toObject(Person::class.java)
                onSuccess(person!!)
            }
    }

    fun loadListGames(
        onLoading: () -> Unit,
        onSuccess: (List<Game>) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        onLoading.invoke()
        firestore.collection(GAMES).get().addOnSuccessListener {
            val games = it.toObjects(Game::class.java)
            onSuccess(games)
        }.addOnFailureListener {
            onFailure(R.string.load_games_error_message)
        }
    }

}