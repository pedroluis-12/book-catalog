package com.pedroluis.projects.bookcatalog.app.list.viewmodel.state

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel

internal sealed class BookListViewModelState {
    object ShowLoading: BookListViewModelState()
    object HideLoading: BookListViewModelState()
    object ShowErrorTryAgainLimit: BookListViewModelState()
    data class ShowError(val tryAgain: Boolean): BookListViewModelState()
    data class ShowBookList(val bookList: List<BookListModel>): BookListViewModelState()
}
