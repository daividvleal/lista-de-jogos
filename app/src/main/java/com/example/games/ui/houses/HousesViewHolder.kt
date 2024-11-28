package com.example.games.ui.houses

import androidx.recyclerview.widget.RecyclerView
import com.example.games.databinding.HouseItemBinding
import com.example.games.domain.model.House

class HousesViewHolder(private val binding: HouseItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(house: House) {
            binding.pet.text = house.animal
            binding.founder.text = house.founder
            binding.element.text = house.element
            binding.ghost.text = house.ghost
        }
}