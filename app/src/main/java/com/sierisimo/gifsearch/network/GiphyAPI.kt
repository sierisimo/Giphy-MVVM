package com.sierisimo.gifsearch.network

import com.sierisimo.gifsearch.network.data.GiphySearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyAPI {
    @GET("gifs/search")
    suspend fun search(
        @Query("q") text: String,
        @Query("offset") page: Int = 0,
        @Query("limit") limit: Int = 5,
        @Query("rating") rating: String = "g"
    ): GiphySearchResponse

    @GET("gifs/trending")
    suspend fun trending(
        @Query("offset") page: Int = 0,
        @Query("limit") limit: Int = 5,
        @Query("rating") rating: String = "g"
    ): GiphySearchResponse
}