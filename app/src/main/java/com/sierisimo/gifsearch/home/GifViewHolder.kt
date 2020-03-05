package com.sierisimo.gifsearch.home

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.databinding.ItemGifResultBinding

class GifViewHolder(
    private val itemGifViewBinding: ItemGifResultBinding
) : RecyclerView.ViewHolder(itemGifViewBinding.root) {

    fun bind(data: GifImageData, clickListener: (GifImageData) -> Unit) {
        Glide.with(itemView)
            .load(data.smallUrl)
            .centerCrop()
            .fitCenter()
            .thumbnail(0.3f)
            .into(itemGifViewBinding.ivItGifSmall)

        itemGifViewBinding.ivItGifSmall.contentDescription = data.title
        itemGifViewBinding.root.setOnClickListener {
            clickListener(data)
        }
    }
}