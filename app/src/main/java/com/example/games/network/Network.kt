package com.example.games.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    private const val BASE_URL = "https://wizard-world-api.herokuapp.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    val service = retrofit.create(Api::class.java)

}