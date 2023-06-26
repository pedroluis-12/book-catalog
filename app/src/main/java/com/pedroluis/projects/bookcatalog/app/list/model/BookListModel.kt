package com.pedroluis.projects.bookcatalog.app.list.model

data class BookListModel(
    val modelId: Int,
    val modelTitle: String,
    val modelAuthors: List<String>,
    val modelSubjects: List<String>
)
