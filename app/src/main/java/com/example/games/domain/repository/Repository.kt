package com.example.games.domain.repository

import com.example.games.R
import com.example.games.domain.model.House
import com.example.games.network.Network
import retrofit2.Call
import retrofit2.Response

class Repository {
    fun getHouses(
        onSuccess: (List<House>) -> Unit,
        onFailure: (Int) -> Unit
    ) {
        Network.service.getHouses().enqueue(object : retrofit2.Callback<List<House>> {

            override fun onResponse(call: Call<List<House>>, response: Response<List<House>>) {
                if (response.isSuccessful) {
                    val houses = response.body()
                    if (houses != null) {
                        onSuccess(houses)
                    } else {
                        onFailure(R.string.error_get_houses)
                    }
                }
            }

            override fun onFailure(call: Call<List<House>>, t: Throwable) {
                onFailure(R.string.error_get_houses)
            }

        })

    }

}