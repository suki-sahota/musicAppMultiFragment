package com.example.musicapi.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi.R
import com.example.musicapi.model.Card

class MusicAdapter(val listener: (item: Card) -> Unit): RecyclerView.Adapter<MusicVH>() {

    var dataSet = listOf<Card>()
        set(value) { // Customized Setter
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicVH {
        return MusicVH(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.music_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: MusicVH, position: Int) {
        holder.onBind(dataSet[position], listener)
    }
}