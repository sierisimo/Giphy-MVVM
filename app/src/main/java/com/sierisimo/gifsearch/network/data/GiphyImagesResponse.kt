package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyImagesResponse(
    @Json(name = "downsized_large") val downsizedLarge: GiphyBaseImageResponse,
    @Json(name = "preview_gif") val previewGif: GiphyBaseImageResponse
)