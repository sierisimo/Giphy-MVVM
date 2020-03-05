package com.sierisimo.gifsearch.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.databinding.ActivityGifDetailBinding
import com.sierisimo.gifsearch.util.GIF_DATA
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GifDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGifDetailBinding

    private val detailViewModel: GifDetailViewModel by viewModel {
        parametersOf(intent.getParcelableExtra<GifImageData>(GIF_DATA))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupToolbar()

        setupTitle()
        setupImage()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.mtAcDetail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTitle() {
        binding.tvAcDetailTitle.text = detailViewModel.title
    }

    private fun setupImage() {
        Glide.with(this)
            .load(detailViewModel.imageUrl)
            .fitCenter()
            .into(binding.ivAcDetail)
    }
}
