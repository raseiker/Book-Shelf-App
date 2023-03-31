package com.example.bookshelfapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse
import com.example.bookshelfapp.data.model.Response
import com.example.bookshelfapp.data.repository.BookRepository
import com.example.bookshelfapp.ui.BookScreenAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for [BookScreen] composable
 */
@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository
): ViewModel() {
    var bookListState: Response<BookResponse> by mutableStateOf(Response.Loading)
        private set
    var bookState: Response<BookModel> by mutableStateOf(Response.Loading)
        private set

    init {
        getAllBooks()
    }

    /**
     * Function that obtain all books from remote repository.
     * Set [bookListState] accordingly
     */
    private fun getAllBooks() = viewModelScope.launch {
        bookState = Response.Loading
        bookListState = try {
            val data = bookRepository.getAllBooks()
            Log.d("Myresponse", "getAllBooks: ${data.items.first().bookInfo.bookImage.image}")
            Response.Success(data.copy(items = data.items.shuffled()))
        } catch (e: Exception) {
            Log.d("Myresponse", "error in: ${e.message}")
            Response.Error(e.message)
        }
    }

    fun onEvent(event: BookScreenAction) {
        when (event) {
            is BookScreenAction.OnBookClicked -> {
//                Log.d("bookClicked", "title: ${event.bookModel.bookInfo.title}")
                getBookById(event.bookModel)
            }
        }
    }

    /**
     * Function that obtain a one Book from API filtered by its id.
     * Set [bookState] accordingly.
     * @param bookModel is the book clicked by the user.
     */
    private fun getBookById(bookModel: BookModel) = viewModelScope.launch {
        bookState = Response.Loading
        bookState = try {
            val data = bookRepository.getBookById(bookModel.id)
            Log.d("bookClicked", "id: ${data.id}")
            Response.Success(data)
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

}