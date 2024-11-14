package com.example.games.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val name: String? = null,
    val year: Int? = null,
    val platform: String? = null,
    val genre: String? = null,
    val multiplayer: Boolean? = null,
    val online: Boolean? = null
): Parcelable