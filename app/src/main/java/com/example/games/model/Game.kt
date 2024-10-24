package com.example.games.model

data class Game(
    val name: String,
    val year: Int,
    val platform: String,
    val genre: String,
    val multiplayer: Boolean,
    val online: Boolean
)