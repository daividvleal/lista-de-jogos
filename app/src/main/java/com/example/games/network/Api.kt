package com.example.games.network

import com.example.games.domain.model.House
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("/Houses")
    fun getHouses(): Call<List<House>>

    @GET("/Houses/{id}")
    fun getHouse(@Path("id") id: String): Call<House>

}