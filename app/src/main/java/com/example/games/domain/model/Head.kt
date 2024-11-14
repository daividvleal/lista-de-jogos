package com.example.games.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Head(
    val firstName: String,
    val id: String,
    val lastName: String
): Parcelable