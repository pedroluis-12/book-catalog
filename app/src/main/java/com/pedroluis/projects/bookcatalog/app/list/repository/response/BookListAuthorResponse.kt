package com.pedroluis.projects.bookcatalog.app.list.repository.response

import com.google.gson.annotations.SerializedName

data class BookListAuthorResponse(
    @SerializedName("name")
    val authorName: String? = null,
    @SerializedName("birth_year")
    val authorBirthYear: Int? = null,
    @SerializedName("death_year")
    val authorDeathYear: Int? = null
)
