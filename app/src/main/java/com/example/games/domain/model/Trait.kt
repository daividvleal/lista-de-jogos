package com.example.games.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trait(
    val id: String,
    val name: String
): Parcelable