package com.sierisimo.gifsearch.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.sierisimo.gifsearch.R
import com.sierisimo.gifsearch.data.EmptyMessage
import com.sierisimo.gifsearch.util.PagedGifImageData

class HomeViewModel(
    private val repository: GifRepository,
    private val validTextRule: (String?) -> Boolean
) : ViewModel() {
    private val searchText = MutableLiveData<String?>()

    /**
     * Public LiveData for outsider to observe.
     * It allows to mutate internally and observe externally.
     *
     * It represents the result from calling the GiphyApi and transforming into something usable
     */
    val gifResultItems: LiveData<PagedGifImageData> = switchMap(searchText) {
        if (it != null) repository.searchGifs(it)
        else repository.trendingGifs()
    }

    private val mutableEmptyMessage = MutableLiveData<EmptyMessage>()

    val emptyMessage: LiveData<EmptyMessage>
        get() = mutableEmptyMessage

    fun onSearchTextSubmitted(text: String?) {
        if (validTextRule(text) && searchText.value != text) {
            mutableEmptyMessage.value = EmptyMessage.EMPTY
            searchText.value = text
        } else {
            mutableEmptyMessage.value = EmptyMessage(R.string.text_no_results)
        }


    }

    fun onTrendingClick() {
        mutableEmptyMessage.value = EmptyMessage.EMPTY
        searchText.value = null
    }
}