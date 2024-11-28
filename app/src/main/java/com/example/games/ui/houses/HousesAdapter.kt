package com.example.games.ui.houses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games.databinding.HouseItemBinding
import com.example.games.domain.model.House

class HousesAdapter(
    private val houses: List<House>,
    private val onClick: (House) -> Unit
): RecyclerView.Adapter<HousesViewHolder>() {

    override fun getItemCount(): Int = houses.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HousesViewHolder {
        val binding = HouseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HousesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HousesViewHolder, position: Int) {
        val house = houses[position]
        holder.bind(house)
        holder.itemView.setOnClickListener {
            onClick(house)
        }
    }


}