package com.example.login_cadastro.ui.home

import androidx.recyclerview.widget.RecyclerView
import com.example.login_cadastro.databinding.ItemGameBinding
import com.example.login_cadastro.model.Game

class GameViewHolder(val binding: ItemGameBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Game){
        binding.txtName.text = game.name
        binding.txtPlatform.text = game.platform
        binding.txtYear.text = game.year.toString()
        binding.txtGenre.text = game.genre

        binding.cbMultiplayed.isChecked = game.multiplayer
        binding.cbOnline.isChecked = game.online
    }
}