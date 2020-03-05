package com.sierisimo.gifsearch.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GiphyImageInfoResponse(
    val type: String,
    val id: String,
    val url: String,
    val slug: String,
    @Json(name = "embed_url") val embedUrl: String,
    val username: String,
    val source: String,
    val title: String,
    val rating: String,
    @Json(name = "content_url") val contentUrl: String,
    @Json(name = "source_tld") val sourceTLD: String,
    @Json(name = "source_post_url") val sourcePostUrl: String,
    @Json(name = "is_sticker") val isSticker: Int,
    @Json(name = "import_datetime") val importDatetime: String,
    @Json(name = "images") val imagesResponse: GiphyImagesResponse
)