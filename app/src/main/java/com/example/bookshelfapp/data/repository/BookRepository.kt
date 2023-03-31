package com.example.bookshelfapp.data.repository

import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse

/**
 * Interface for interact with Book Api
 */
interface BookRepository {
    suspend fun getAllBooks(): BookResponse

    suspend fun getBookById(idBook: String): BookModel
}