package com.example.giphy.ui.gif_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.giphy.domain.models.GifModel

class GifDiffCallback: DiffUtil.ItemCallback<GifModel>()  {
    override fun areItemsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: GifModel, newItem: GifModel): Boolean {
       return newItem == oldItem
    }
}