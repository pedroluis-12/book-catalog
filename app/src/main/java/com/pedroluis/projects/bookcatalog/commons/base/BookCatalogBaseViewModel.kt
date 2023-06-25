package com.pedroluis.projects.bookcatalog.commons.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

internal abstract class BookCatalogBaseViewModel: ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}
