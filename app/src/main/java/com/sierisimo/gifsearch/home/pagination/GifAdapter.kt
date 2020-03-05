package com.sierisimo.gifsearch.home.pagination

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.databinding.ItemGifResultBinding
import com.sierisimo.gifsearch.home.GifViewHolder

class GifAdapter(
    comparator: DiffUtil.ItemCallback<GifImageData>,
    private val itemClickListener: (GifImageData) -> Unit
) : PagedListAdapter<GifImageData, GifViewHolder>(comparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val viewBinding = ItemGifResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return GifViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifData = getItem(position)
        gifData?.let { holder.run { bind(it, itemClickListener) } }
    }
}