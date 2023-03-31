package com.example.bookshelfapp.data.repository

import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse
import com.example.bookshelfapp.data.remote.BookAPIService
import javax.inject.Inject

/**
 * Implementation class for [BookRepository]
 */
class BookRepositoryImpl @Inject constructor(
    private val apiService: BookAPIService
): BookRepository {
    override suspend fun getAllBooks(): BookResponse {
        return apiService.getAllBooks()
    }

    override suspend fun getBookById(idBook: String): BookModel {
        return apiService.getBookById(idBook)
    }
}