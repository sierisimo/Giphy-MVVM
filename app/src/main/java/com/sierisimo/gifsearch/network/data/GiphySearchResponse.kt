package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphySearchResponse(
    val data: List<GiphyImageInfoResponse>,
    val pagination: GiphyPagination,
    val meta: GiphyMetaResponse
)