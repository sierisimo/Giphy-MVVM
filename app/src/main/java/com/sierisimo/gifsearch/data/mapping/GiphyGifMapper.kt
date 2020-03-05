package com.sierisimo.gifsearch.data.mapping

import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.network.data.GiphyImagesResponse
import com.sierisimo.gifsearch.network.data.GiphySearchResponse

class GiphyGifMapper(
    private val smallImageMapper: Mapper<GiphyImagesResponse, String>,
    private val largeImageMapper: Mapper<GiphyImagesResponse, String>
) : Mapper<GiphySearchResponse, List<GifImageData>> {
    override fun map(input: GiphySearchResponse): List<GifImageData> {
        val data = input.data
        return data.map {
            GifImageData(
                id = it.id,
                title = it.title,
                smallUrl = smallImageMapper.map(it.imagesResponse),
                largeUrl = largeImageMapper.map(it.imagesResponse)
            )
        }
    }
}