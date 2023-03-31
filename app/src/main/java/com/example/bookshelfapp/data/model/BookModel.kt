package com.example.bookshelfapp.data.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    val kind: String,
    val totalItems: Int,
    val items: List<BookModel>
)

data class BookModel (
    @SerializedName("id") val id: String,
    @SerializedName("volumeInfo") val bookInfo: BookInfo,
)

data class BookInfo(
    @SerializedName("title") val title: String,
    @SerializedName("authors") val author: List<String>,
    @SerializedName("imageLinks") val bookImage: BookImage,
)

data class BookImage(
    @SerializedName("thumbnail") val image: String,
)