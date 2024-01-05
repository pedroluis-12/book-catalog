package com.pedroluis.projects.bookcatalog.commons.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookCatalogApi {

    private const val BOOK_CATALOG_URL: String = "https://gutendex.com"

    fun <T> setupProviderApi(api: Class<T>): T {
        val retrofitClient = Retrofit.Builder()
            .baseUrl(BOOK_CATALOG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitClient.create(api)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> Response<T>.getResponse(responseClass: Class<T>): T = if (this.isSuccessful) {
        this.body() as T
    } else {
        throw Exception()
    }
}
