package com.pedroluis.projects.bookcatalog.app.list.interactor.state

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel

internal object BookListInteractorState {

    sealed class BookListState {
        data class DisplayList(val bookList: List<BookListModel>): BookListState()
        data class DisplayError(val tryAgain: Boolean): BookListState()
        object DisplayTryAgainErrorLimit: BookListState()
    }
}