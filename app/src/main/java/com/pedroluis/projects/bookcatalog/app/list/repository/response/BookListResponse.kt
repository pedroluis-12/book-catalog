package com.pedroluis.projects.bookcatalog.app.list.repository.response

import com.google.gson.annotations.SerializedName

internal data class BookListResponse(
    @SerializedName("count")
    val responseCount: Int? = null,
    @SerializedName("next")
    val responseNext: String? = null,
    @SerializedName("results")
    val responseResults: List<BookListResultResponse>? = null
)
