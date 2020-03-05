package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyMetaResponse(
    val status: Int,
    @Json(name = "msg") val message: String,
    @Json(name = "response_id") val responseId: String
)