package com.sierisimo.gifsearch.home.pagination

/**
 * This class allows to use the Search or Trending service in the same [DataSource] without
 * having to duplicate code.
 */
sealed class GifDataRequest {
    class Search(val value: String) : GifDataRequest()

    object Trending : GifDataRequest()
}