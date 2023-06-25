package com.pedroluis.projects.bookcatalog.app.list.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pedroluis.projects.bookcatalog.app.list.interactor.BookListInteractorImpl
import com.pedroluis.projects.bookcatalog.app.list.repository.BookListRepositoryImpl
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.BookListViewModel

internal class BookListViewModelFactory: ViewModelProvider.Factory {

    private val repository = BookListRepositoryImpl()
    private val interactor = BookListInteractorImpl(repository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        BookListViewModel(interactor) as T
}
