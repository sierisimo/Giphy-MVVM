package com.sierisimo.gifsearch.home

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.observe
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sierisimo.gifsearch.R
import com.sierisimo.gifsearch.databinding.ActivityMainBinding
import com.sierisimo.gifsearch.home.pagination.GifAdapter
import com.sierisimo.gifsearch.home.pagination.GifImageDataComparator
import com.sierisimo.gifsearch.util.AppNavigation
import com.sierisimo.gifsearch.util.hide
import com.sierisimo.gifsearch.util.show
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), AppNavigation {
    private lateinit var binding: ActivityMainBinding

    private val homeViewModel by viewModel<HomeViewModel>()

    private val adapter = GifAdapter(GifImageDataComparator()) {
        launchDetail(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setupToolbar()
        setupList()

        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_main, menu)

        val trending = menu?.findItem(R.id.item_trending)
        trending?.setOnMenuItemClickListener {
            homeViewModel.onTrendingClick()
            true
        }

        val searchView = menu?.findItem(R.id.item_search)?.actionView as? SearchView
        searchView?.apply {
            queryHint = getString(R.string.hint_search)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(text: String?): Boolean {
                    homeViewModel.onSearchTextSubmitted(text)
                    return false
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    return false
                }
            })
        }

        return true
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.mtAcMain)
    }

    private fun setupList() {
        binding.rvAcMainResults.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        binding.rvAcMainResults.adapter = adapter
    }

    private fun observeViewModel() {
        homeViewModel.emptyMessage.observe(this) {
            Log.d("Ohfuck  ->", "Ohfuck:EMPTY $it")
            if (it.shouldShow) {
                binding.hideResultList()
                binding.showEmptyMessage(it.textRes)
            } else {
                binding.hideEmptyMessage()
                binding.showResultList()
            }
        }

        homeViewModel.gifResultItems.observe(this) { pagedList ->
            binding.showResultList()
            adapter.submitList(pagedList)
        }
    }

    /**
     * Not the best practice as the binding classes are generated… but I got annoyed by accessing constantly to
     * binding.something.property = …
     * binding.something.method(…)
     */
    private fun ActivityMainBinding.showEmptyMessage(textRes: Int?) {
        tvAcEmptySearch.show()
        textRes?.let { tvAcEmptySearch.text = getString(it) }
    }

    private fun ActivityMainBinding.hideEmptyMessage() {
        tvAcEmptySearch.hide()
    }

    private fun ActivityMainBinding.showResultList() {
        rvAcMainResults.show()
    }

    private fun ActivityMainBinding.hideResultList() {
        rvAcMainResults.hide()
    }
}
