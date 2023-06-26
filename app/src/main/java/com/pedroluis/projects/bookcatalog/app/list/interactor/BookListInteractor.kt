package com.pedroluis.projects.bookcatalog.app.list.interactor

import com.pedroluis.projects.bookcatalog.app.list.interactor.state.BookListInteractorState

internal interface BookListInteractor {

    suspend fun getDisplayBookList(tryAgain: Boolean): BookListInteractorState.BookListState
}