package com.sierisimo.gifsearch.network

import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Wrapping function to get the [HttpLoggingInterceptor] that will be used with
 * the network client.
 *
 * @param level one of [HttpLoggingInterceptor.Level] defaults to [HttpLoggingInterceptor.Level.BASIC]
 *
 * @return an [Interceptor] that logs calls
 */
fun httpLoggingInterceptor(
    level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BASIC
): Interceptor =
    HttpLoggingInterceptor().apply {
        setLevel(level)
    }

/**
 * Simple interceptor to add the auth key needed for calling the api. This method
 * should evolve to allow include in headers instead of simple query parameter. Also
 * name of the value should change later
 */
fun apiKeyParamInterceptor(value: String) =
    Interceptor { chain ->
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("api_key", value)
            .build()

        chain.proceed(
            request.newBuilder()
                .url(url)
                .build()
        )
    }

/**
 * Function to change the [Converter.Factory] that will be used for the parsing of responses
 * It's a simple shortcut to change [MoshiConverterFactory] for a different instance of a [Converter.Factory]
 * if desired in the future
 */
fun jsonConverterFactory(): Converter.Factory = MoshiConverterFactory.create()