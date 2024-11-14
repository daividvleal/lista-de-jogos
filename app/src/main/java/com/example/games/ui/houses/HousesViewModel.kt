package com.example.games.ui.houses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.games.domain.model.House
import com.example.games.domain.repository.Repository

class HousesViewModel: ViewModel() {

    private val repository = Repository()
    private val _houses = MutableLiveData<List<House>>()
    val houses: LiveData<List<House>>
        get() = _houses

    private val _errorLoadingHouses = MutableLiveData<Int>()
    val errorLoadingHouses: LiveData<Int>
        get() = _errorLoadingHouses


    fun loadHouses(){
        repository.getHouses(
            onSuccess = {
                _houses.value = it
            },
            onFailure = {
                _errorLoadingHouses.value = it
            }
        )
    }

}