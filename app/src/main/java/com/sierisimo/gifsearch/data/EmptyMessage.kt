package com.sierisimo.gifsearch.data

import androidx.annotation.StringRes

data class EmptyMessage(
    @StringRes val textRes: Int? = null
) {
    val shouldShow: Boolean
        get() = textRes != null

    companion object {
        val EMPTY = EmptyMessage(null)
    }
}