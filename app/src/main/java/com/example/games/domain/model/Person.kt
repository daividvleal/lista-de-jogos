package com.example.games.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val name: String? = null,
    val phone: String? = null,
    val hobby: String? = null,
    val email: String? = null,
    var uId: String? = null,
    val house: House? = null
): Parcelable