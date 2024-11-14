package com.example.games.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.games.databinding.ItemGameBinding
import com.example.games.domain.model.Game

class GameAdapter(
    private val games: List<Game>,
    private val layoutInflater: LayoutInflater,
) : RecyclerView.Adapter<GameViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = ItemGameBinding.inflate(layoutInflater, parent, false)
        return GameViewHolder(view)
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

}