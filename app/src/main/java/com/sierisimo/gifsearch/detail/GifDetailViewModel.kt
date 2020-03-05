package com.sierisimo.gifsearch.detail

import androidx.lifecycle.ViewModel
import com.sierisimo.gifsearch.data.GifImageData

class GifDetailViewModel(
    gifImageData: GifImageData
) : ViewModel() {
    val imageUrl = gifImageData.largeUrl
    val title = gifImageData.title
}