package com.pedroluis.projects.bookcatalog.app.list.repository

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel

internal interface BookListRepository {

    suspend fun getBookList(page: Int): List<BookListModel>
}
