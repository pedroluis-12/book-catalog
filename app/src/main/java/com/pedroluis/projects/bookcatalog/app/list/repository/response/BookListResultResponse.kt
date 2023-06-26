package com.pedroluis.projects.bookcatalog.app.list.repository.response

import com.google.gson.annotations.SerializedName

data class BookListResultResponse(
    @SerializedName("id")
    val resultId: Int? = null,
    @SerializedName("title")
    val resultTitle: String? = null,
    @SerializedName("authors")
    val resultAuthors: List<BookListAuthorResponse>? = null,
    @SerializedName("subjects")
    val resultSubjects: List<String>? = null
)