package com.pedroluis.projects.bookcatalog.app.list.repository

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.app.list.repository.api.BookListApi
import com.pedroluis.projects.bookcatalog.app.list.repository.mapper.BookListMapperRepository
import com.pedroluis.projects.bookcatalog.app.list.repository.response.BookListResponse
import com.pedroluis.projects.bookcatalog.commons.api.BookCatalogApi
import com.pedroluis.projects.bookcatalog.commons.api.BookCatalogApi.getResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class BookListRepositoryImpl : BookListRepository {

    override suspend fun getBookList(page: Int): List<BookListModel> {
        val response: BookListResponse
        val api = BookCatalogApi.setupProviderApi(api = BookListApi::class.java)
        withContext(Dispatchers.IO) {
            response = api.getBookList(page).getResponse(BookListResponse::class.java)
        }

        return BookListMapperRepository.convertResponseToModel(response)
    }
}
