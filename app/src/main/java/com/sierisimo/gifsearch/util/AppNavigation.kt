package com.sierisimo.gifsearch.util

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.detail.GifDetailActivity

const val GIF_DATA = "GIF_DATA"

/**
 * Create a single point for extension functions that abstract how to launch new screens
 */
interface AppNavigation {
    fun Context.launchDetail(gifImageData: GifImageData) {
        launchActivity<GifDetailActivity> {
            putExtra(GIF_DATA, gifImageData)
        }
    }
}

/**
 * Helper function to reduce boilerplate and allow an small "DSL"
 */
private inline fun <reified A : AppCompatActivity> Context.launchActivity(intentBlock: Intent.() -> Unit = {}) {
    val intent = Intent(this, A::class.java)
    intent.intentBlock()
    startActivity(intent)
}