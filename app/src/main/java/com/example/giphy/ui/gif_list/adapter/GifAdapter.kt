package com.example.giphy.ui.gif_list.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.giphy.databinding.CellGifBinding
import com.example.giphy.domain.models.GifModel

class GifAdapter : ListAdapter<GifModel, GifAdapter.GifViewHolder>(GifDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val binding = CellGifBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GifViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifModel = getItem(position)

        holder.bind(gifModel)
    }

    inner class GifViewHolder(private val binding: CellGifBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(gifModel: GifModel) {
            itemView.apply {
                binding.title.text = gifModel.title
                loadGif(gifModel.gifUrl)
            }
        }

        private fun loadGif(url: String) {
            Glide.with(context)
                .load(url)
                .into(binding.gif)
        }
    }
}