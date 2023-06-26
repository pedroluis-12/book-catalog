package com.pedroluis.projects.bookcatalog.app.list.repository.mapper

import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.app.list.repository.response.BookListAuthorResponse
import com.pedroluis.projects.bookcatalog.app.list.repository.response.BookListResponse

internal object BookListMapperRepository {

    fun convertResponseToModel(response: BookListResponse): List<BookListModel> {
        val model = mutableListOf<BookListModel>()
        response.responseResults?.forEach { item ->
            model.add(
                BookListModel(
                    modelId = item.resultId ?: 0,
                    modelTitle = item.resultTitle ?: "",
                    modelAuthors = setupAuthors(item.resultAuthors),
                    modelSubjects = item.resultSubjects ?: emptyList()

                )
            )
        }
        return model
    }

    private fun setupAuthors(authors: List<BookListAuthorResponse>?): List<String> {
        val namesOfAuthors = mutableListOf<String>()
        authors?.forEach { item ->
            if (!item.authorName.isNullOrBlank()) {
                namesOfAuthors.add(item.authorName)
            }
        }
        return namesOfAuthors
    }
}