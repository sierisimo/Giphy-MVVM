package com.sierisimo.gifsearch.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GifImageData(
    val id: String,
    val smallUrl: String,
    val title: String,
    val largeUrl: String
) : Parcelable