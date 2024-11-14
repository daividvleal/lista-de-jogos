package com.example.games.ui.auth

import androidx.lifecycle.ViewModel
import com.example.games.model.Game
import com.example.games.model.Person
import com.example.games.util.GAMES
import com.example.games.util.USERS
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()


    val games = listOf(
        Game("Super Mario Odyssey", 2017, "Nintendo Switch", "Platformer", false, false),
        Game("The Legend of Zelda: Breath of the Wild", 2017, "Nintendo Switch", "Action-adventure", false, false),
        Game("Red Dead Redemption 2", 2018, "PlayStation 4", "Action-adventure", true, true),
        Game("God of War", 2018, "PlayStation 4", "Action-adventure", false, false),
        Game("Grand Theft Auto V", 2013, "PlayStation 4", "Action-adventure", true, true),
        Game("The Witcher 3: Wild Hunt", 2015, "PlayStation 4", "Role-playing", false, false),
        Game("Minecraft", 2011, "PC", "Sandbox", true, true),
        Game("Fortnite", 2017, "PC", "Battle royale", true, true),
        Game("League of Legends", 2009, "PC", "MOBA", true, true),
        Game("Counter-Strike: Global Offensive", 2012, "PC", "First-person shooter", true, true),
        Game("Dota 2", 2013, "PC", "MOBA", true, true),
        Game("Overwatch", 2016, "PC", "First-person shooter", true, true),
        Game("Call of Duty: Modern Warfare", 2019, "PC", "First-person shooter", true, true),
        Game("Apex Legends", 2019, "PC", "Battle royale", true, true),
        Game("PUBG: Battlegrounds", 2017, "PC", "Battle royale", true, true),
        Game("Valorant", 2020, "PC", "First-person shooter", true, true),
        Game("Among Us", 2018, "PC", "Social deduction", true, true),
        Game("Genshin Impact", 2020, "PC", "Action role-playing", true, true),
        Game("Rocket League", 2015, "PC", "Sports", true, true),
        Game("Rainbow Six Siege", 2015, "PC", "First-person shooter", true, true),
        Game("Destiny 2", 2017, "PC", "First-person shooter", true, true),
        Game("The Elder Scrolls V: Skyrim", 2011, "PC", "Role-playing", false, false),
        Game("Grand Theft Auto: San Andreas", 2004, "PC", "Action-adventure", false, false),
        Game("Terraria", 2011, "PC", "Action-adventure", true, true),
        Game("Stardew Valley", 2016, "PC", "Simulation role-playing", true, true),
        Game("Team Fortress 2", 2007, "PC", "First-person shooter", true, true),
        Game("Portal 2", 2011, "PC", "Puzzle-platformer", false, false),
        Game("Half-Life 2", 2004, "PC", "First-person shooter", false, false),
        Game("The Sims 4", 2014, "PC", "Life simulation", false, false),
        Game("Civilization VI", 2016, "PC", "Turn-based strategy", true, true)
    )

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


        games.forEach { games ->
            firestore.collection(GAMES).document().set(games)
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