package com.sierisimo.gifsearch.data.mapping

interface Mapper<in I, out O> {
    fun map(input: I): O
}