package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyPagination(
    @Json(name = "total_count")
    val totalCount: Int,
    val count: Int,
    val offset: Int
)