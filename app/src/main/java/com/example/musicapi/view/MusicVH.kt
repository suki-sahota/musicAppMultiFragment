package com.example.musicapi.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi.R
import com.example.musicapi.model.Card
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.music_item_layout.view.*

class MusicVH(private val itemMusic: View): RecyclerView.ViewHolder(itemMusic) {

    fun onBind(item: Card, listener: (item: Card) -> Unit) {
        Picasso.get().load(item.artworkUrl60).into(itemMusic.iv_album_cover)
        itemMusic.tv_title.text = item.collectionName
        itemMusic.tv_artist.text = item.artistName
        itemMusic.tv_price.text =
            itemMusic.context.getString(R.string.album_price, item.trackPrice.toString())
        itemMusic.setOnClickListener {
            listener.invoke(item)
        }
    }
}