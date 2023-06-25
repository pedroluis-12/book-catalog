package com.pedroluis.projects.bookcatalog.app.list.repository

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.app.list.repository.api.BookListApi
import com.pedroluis.projects.bookcatalog.app.list.repository.mapper.BookListMapperRepository
import com.pedroluis.projects.bookcatalog.app.list.repository.response.BookListResponse
import com.pedroluis.projects.bookcatalog.commons.api.BookCatalogApi
import com.pedroluis.projects.bookcatalog.commons.api.BookCatalogApi.response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class BookListRepositoryImpl : BookListRepository {

    override suspend fun getBookList(page: Int): List<BookListModel> {
        val response: BookListResponse
        val api = setupProviderApi()
        withContext(Dispatchers.IO) {
            response = api.getBookList(page).response(BookListResponse::class.java)
        }

        return BookListMapperRepository.convertResponseToModel(response)
    }

    private fun setupProviderApi(): BookListApi {
        val retrofitClient = Retrofit.Builder()
            .baseUrl(BookCatalogApi.BOOK_CATALOG_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitClient.create(BookListApi::class.java)
    }
}