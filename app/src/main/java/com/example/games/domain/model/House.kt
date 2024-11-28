package com.example.games.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class House(
    val animal: String? = null,
    val commonRoom: String? = null,
    val element: String? = null,
    val founder: String? = null,
    val ghost: String? = null,
    val houseColours: String? = null,
    val id: String? = null,
    val name: String? = null
): Parcelable