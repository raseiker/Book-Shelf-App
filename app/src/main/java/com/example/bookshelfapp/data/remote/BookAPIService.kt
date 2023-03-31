package com.example.bookshelfapp.data.remote

import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/**
 * Interface for CRUD operation Book Api that uses Retrofit
 */
interface BookAPIService {

    @GET("volumes?q=inauthor:cicero")
    suspend fun getAllBooks(): BookResponse

    @GET("volumes/{idBook}")
    suspend fun getBookById(@Path("idBook") idBook: String): BookModel
}