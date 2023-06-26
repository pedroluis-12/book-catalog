package com.pedroluis.projects.bookcatalog.app.list.interactor

import com.pedroluis.projects.bookcatalog.app.list.interactor.state.BookListInteractorState
import com.pedroluis.projects.bookcatalog.app.list.repository.BookListRepository

internal class BookListInteractorImpl(
    private val repository: BookListRepository
) : BookListInteractor {

    override suspend fun getDisplayBookList(
        tryAgain: Boolean
    ): BookListInteractorState.BookListState = try {
        val listData = repository.getBookList(1)
        when {
            listData.isNotEmpty() -> BookListInteractorState.BookListState.DisplayList(listData)
            else -> BookListInteractorState.BookListState.DisplayError(tryAgain)
        }
    } catch (exception: Exception) {
        BookListInteractorState.BookListState.DisplayError(tryAgain)
    }
}