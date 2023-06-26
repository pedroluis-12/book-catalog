package com.pedroluis.projects.bookcatalog.app.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pedroluis.projects.bookcatalog.app.list.interactor.BookListInteractor
import com.pedroluis.projects.bookcatalog.app.list.interactor.state.BookListInteractorState
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.state.BookListViewModelState
import com.pedroluis.projects.bookcatalog.commons.base.BookCatalogBaseViewModel
import kotlinx.coroutines.launch

internal class BookListViewModel(
    private val interactor: BookListInteractor
): BookCatalogBaseViewModel() {

    private val listState: MutableLiveData<BookListViewModelState> = MutableLiveData()
    val listViewState: LiveData<BookListViewModelState> = listState

    fun getList(tryAgain: Boolean = false) {
        viewModelScope.launch {
            try {
                listState.value = BookListViewModelState.ShowLoading
                val state = interactor.getDisplayBookList(tryAgain)
                handleBookListState(state)
            } catch (exception: Exception) {
                listState.value = BookListViewModelState.ShowError(tryAgain)
            } finally {
                listState.value = BookListViewModelState.HideLoading
            }
        }
    }

    private fun handleBookListState(state: BookListInteractorState.BookListState) {
        when (state) {
            is BookListInteractorState.BookListState.DisplayList ->
                listState.value = BookListViewModelState.ShowBookList(state.bookList)

            is BookListInteractorState.BookListState.DisplayError ->
                listState.value = BookListViewModelState.ShowError(state.tryAgain)

            is BookListInteractorState.BookListState.DisplayTryAgainErrorLimit ->
                listState.value = BookListViewModelState.ShowErrorTryAgainLimit
        }
    }
}
