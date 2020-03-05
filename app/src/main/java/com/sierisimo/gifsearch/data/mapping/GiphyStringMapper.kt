package com.sierisimo.gifsearch.data.mapping

import com.sierisimo.gifsearch.network.data.GiphyImagesResponse

class SmallGiphyStringMapper :
    Mapper<GiphyImagesResponse, String> {
    override fun map(input: GiphyImagesResponse): String = input.previewGif.url
}

class LargeGiphyStringMapper :
    Mapper<GiphyImagesResponse, String> {
    override fun map(input: GiphyImagesResponse): String = input.downsizedLarge.url
}