package com.sierisimo.gifsearch.home.pagination

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.sierisimo.gifsearch.data.GifImageData

class GifImageDataComparator : DiffUtil.ItemCallback<GifImageData>() {
    override fun areItemsTheSame(oldItem: GifImageData, newItem: GifImageData): Boolean {
        Log.i("ComparatorItems", "Items: ${oldItem.id} && ${newItem.id} ")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GifImageData, newItem: GifImageData): Boolean {
        Log.i("ComparatorContents", "Items: ${oldItem == newItem} ")
        return oldItem == newItem
    }
}