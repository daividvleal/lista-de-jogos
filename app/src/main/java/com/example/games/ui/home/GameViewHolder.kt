package com.example.games.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.games.databinding.ItemGameBinding
import com.example.games.model.Game

class GameViewHolder(val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game){
        binding.txtName.text = game.name
        binding.txtPlatform.text = game.platform
        binding.txtYear.text = game.year.toString()
        binding.txtGenre.text = game.genre

        binding.cbMultiplayed.isChecked = game.multiplayer ?: false
        binding.cbOnline.isChecked = game.online ?: false
    }
}