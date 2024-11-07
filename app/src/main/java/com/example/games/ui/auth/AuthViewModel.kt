package com.example.games.ui.auth

import androidx.lifecycle.ViewModel
import com.example.games.model.Person
import com.example.games.util.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            onFailure.invoke()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure()
                }
        }
    }

    fun createUser(
        person: Person,
        password: String,
        confirmPassword: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        if (person.email.isNullOrEmpty()) {
            onFailure("Email is required")
            return
        }

        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        if (!emailRegex.matches(person.email)) {
            onFailure( "Invalid email format")
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(person.email).matches()) {
            onFailure("Invalid email format")
            return
        }

        if (password.isBlank()) {
            onFailure("Password is required")
            return
        }

        if (password.length < 6) {
            onFailure("Password must be at least 6 characters")
            return
        }

        if (confirmPassword.isBlank()) {
            onFailure("Confirm password is required")
            return
        }

        if (person.name.isNullOrEmpty()) {
            onFailure("Name is required")
            return
        }

        if (person.phone.isNullOrEmpty()) {
           onFailure("Phone is required")
            return
        }

        if (!android.util.Patterns.PHONE.matcher(person.phone).matches()) {
            onFailure("Invalid phone number")
            return
        }

        if (person.hobby.isNullOrEmpty()) {
            onFailure("Hobby is required")
            return
        }

        if (password != confirmPassword) {
            onFailure("Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(
            person.email,
            password
        ).addOnFailureListener {
            onFailure("Falha na criação do seu usuário! Tente novamente por favor!")
        }.addOnSuccessListener {
            val user = auth.currentUser
            person.uId = user?.uid
            saveUser(person, onSuccess, onFailure)
        }
    }

    private fun saveUser(
        person: Person,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ){
        firestore.collection(USERS).document(person.uId!!).set(person)
            .addOnSuccessListener {
            onSuccess.invoke()
        }.addOnFailureListener {
            onFailure.invoke("Falha na criação do seu usuário! Tente novamente por favor!")
        }
    }

}