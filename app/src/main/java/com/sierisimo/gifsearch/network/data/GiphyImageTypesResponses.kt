package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyBaseImageResponse(
    val height: String,
    val size: String,
    val url: String,
    val width: String
)