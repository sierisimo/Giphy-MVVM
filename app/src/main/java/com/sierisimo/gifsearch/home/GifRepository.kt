package com.sierisimo.gifsearch.home

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.data.mapping.Mapper
import com.sierisimo.gifsearch.home.pagination.GifDataRequest
import com.sierisimo.gifsearch.home.pagination.GifDataSourceFactory
import com.sierisimo.gifsearch.network.GiphyAPI
import com.sierisimo.gifsearch.network.data.GiphySearchResponse
import com.sierisimo.gifsearch.util.PagedGifImageData

class GifRepository(
    private val api: GiphyAPI,
    private val mapper: Mapper<GiphySearchResponse, List<GifImageData>>
) {
    private companion object {
        const val PAGE_SIZE = 10
        const val INITIAL_LOAD_SIZE = 20
        const val PREFETCH_DISTANCE = 5
    }

    private val pagedListConfig = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .setEnablePlaceholders(true)
        .build()

    /**
     * Run a search over the Giphy API.
     *
     * @param text A string to use in /search?q={text}
     *
     * @return a new instance of a livedata publishing a [PagedList] of gif data
     */
    fun searchGifs(text: String): LiveData<PagedGifImageData> =
        liveDataPagedList(GifDataRequest.Search(text))

    /**
     * Get the top trending gifs from Giphy
     */
    fun trendingGifs(): LiveData<PagedGifImageData> =
        liveDataPagedList(GifDataRequest.Trending)

    private fun liveDataPagedList(dataRequest: GifDataRequest): LiveData<PagedGifImageData> {
        val factory = GifDataSourceFactory(dataRequest, api, mapper)
        return LivePagedListBuilder(factory, pagedListConfig).build()
    }
}