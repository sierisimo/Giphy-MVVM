package com.sierisimo.gifsearch.di

import com.sierisimo.gifsearch.data.GifImageData
import com.sierisimo.gifsearch.data.mapping.GiphyGifMapper
import com.sierisimo.gifsearch.data.mapping.LargeGiphyStringMapper
import com.sierisimo.gifsearch.data.mapping.Mapper
import com.sierisimo.gifsearch.data.mapping.SmallGiphyStringMapper
import com.sierisimo.gifsearch.detail.GifDetailViewModel
import com.sierisimo.gifsearch.home.GifRepository
import com.sierisimo.gifsearch.home.HomeViewModel
import com.sierisimo.gifsearch.network.GiphyAPI
import com.sierisimo.gifsearch.network.apiKeyParamInterceptor
import com.sierisimo.gifsearch.network.httpLoggingInterceptor
import com.sierisimo.gifsearch.network.jsonConverterFactory
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

object AppModules {
    private val networkModule = module {
        single {
            //Build an OkHttpClient that can be shared across libraries
            OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor(apiKeyParamInterceptor(getProperty("api_key")))
                .build()
        }

        single {
            //Single retrofit client, can be used to build multiple APIs
            Retrofit.Builder()
                .baseUrl(getProperty<String>("base_url"))
                .addConverterFactory(jsonConverterFactory())
                .client(get<OkHttpClient>())
                .build()
        }

        single<GiphyAPI> {
            val retrofit: Retrofit = get()
            retrofit.create()
        }
    }

    private val homeModule = module {
        factory {
            GiphyGifMapper(
                get(named("small")),
                get(named("large"))
            )
        } bind Mapper::class

        factory(named("small")) { SmallGiphyStringMapper() } bind Mapper::class
        factory(named("large")) { LargeGiphyStringMapper() } bind Mapper::class

        factory { GifRepository(get(), get()) }

        factory {
            val textValidationRule: (String?) -> Boolean =
                { text -> text != null && text.isNotBlank() }
            textValidationRule
        }

        viewModel { HomeViewModel(get(), get()) }
    }

    private val detailModule = module {
        viewModel { (gifImageData: GifImageData) -> GifDetailViewModel(gifImageData) }
    }

    operator fun invoke(): List<Module> = listOf(networkModule, homeModule, detailModule)
}