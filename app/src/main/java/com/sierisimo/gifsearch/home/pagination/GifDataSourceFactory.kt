package com.sierisimo.gifsearch.home.pagination

import androidx.paging.DataSource
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.data.mapping.Mapper
import com.sierisimo.gifsearch.network.GiphyAPI
import com.sierisimo.gifsearch.network.data.GiphySearchResponse

class GifDataSourceFactory(
    private val request: GifDataRequest,
    private val giphyAPI: GiphyAPI,
    private val mapper: Mapper<GiphySearchResponse, List<GifImageData>>
) : DataSource.Factory<Int, GifImageData>() {
    override fun create(): DataSource<Int, GifImageData> = GifDataSource(request, giphyAPI, mapper)
}