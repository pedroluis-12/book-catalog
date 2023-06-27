package com.pedroluis.projects.bookcatalog.app.list.interactor

import com.pedroluis.projects.bookcatalog.app.list.interactor.state.BookListInteractorState
import com.pedroluis.projects.bookcatalog.app.list.repository.BookListRepository

internal class BookListInteractorImpl(
    private val repository: BookListRepository
) : BookListInteractor {

    override suspend fun getDisplayBookList(
        tryAgain: Boolean
    ): BookListInteractorState.BookListState = try {

        setupTryAgain(tryAgain)
        val listData = repository.getBookList(1)

        when {
            isTryAgainLimit() -> BookListInteractorState.BookListState.DisplayTryAgainErrorLimit
            listData.isNotEmpty() -> BookListInteractorState.BookListState.DisplayList(listData)
            else -> BookListInteractorState.BookListState.DisplayError(tryAgain)
        }
    } catch (exception: Exception) {
        when {
            isTryAgainLimit() -> BookListInteractorState.BookListState.DisplayTryAgainErrorLimit
            else -> BookListInteractorState.BookListState.DisplayError(tryAgain)
        }
    }

    private fun setupTryAgain(tryAgain: Boolean) {
        if (tryAgain) {
            tryAgainCount++
        }
    }

    private fun isTryAgainLimit() = if (tryAgainCount >= TRY_AGAIN_LIMIT) {
        tryAgainCount = 0
        true
    } else {
        false
    }

    companion object {
        private const val TRY_AGAIN_LIMIT: Int = 3
        private var tryAgainCount: Int = 0
    }
}