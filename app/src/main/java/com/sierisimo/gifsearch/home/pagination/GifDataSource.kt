package com.sierisimo.gifsearch.home.pagination

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.data.mapping.Mapper
import com.sierisimo.gifsearch.network.GiphyAPI
import com.sierisimo.gifsearch.network.data.GiphySearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class GifDataSource(
    private val request: GifDataRequest,
    private val giphyAPI: GiphyAPI,
    private val mapper: Mapper<GiphySearchResponse, List<GifImageData>>
) : PageKeyedDataSource<Int, GifImageData>(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private companion object {
        const val INITIAL_PAGE = 0
        const val INCREMENT_PAGE_BY = 1
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GifImageData>
    ) {
        launch {
            callback.onResult(mapper.map(getResponse()), null, INCREMENT_PAGE_BY)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, GifImageData>) {
        //Ignored as load initial starts at 0
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, GifImageData>) {
        Log.w("LoadAfter ∑", "Key: ${params.key}")
        launch {
            callback.onResult(
                mapper.map(getResponse(params.key)),
                params.key + INCREMENT_PAGE_BY
            )
        }
    }

    private suspend fun getResponse(page: Int = INITIAL_PAGE): GiphySearchResponse =
        when (request) {
            is GifDataRequest.Search -> giphyAPI.search(request.value, page)
            is GifDataRequest.Trending -> giphyAPI.trending(page)
        }
}