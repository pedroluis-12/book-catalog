package com.pedroluis.projects.bookcatalog.app.list.repository.api

import com.pedroluis.projects.bookcatalog.app.list.repository.response.BookListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface BookListApi {

    @GET("/books")
    suspend fun getBookList(
        @Query("page") page: Int
    ): Response<BookListResponse>
}
