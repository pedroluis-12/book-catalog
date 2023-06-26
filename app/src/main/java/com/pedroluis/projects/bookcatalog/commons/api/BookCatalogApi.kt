package com.pedroluis.projects.bookcatalog.commons.api

import retrofit2.Response

object BookCatalogApi {

    const val BOOK_CATALOG_URL: String = "https://gutendex.com"

    @Suppress("UNCHECKED_CAST")
    fun <T> Response<T>.getResponse(responseClass: Class<T>): T = if (this.isSuccessful) {
        this.body() as T
    } else {
        throw Exception()
    }
}
