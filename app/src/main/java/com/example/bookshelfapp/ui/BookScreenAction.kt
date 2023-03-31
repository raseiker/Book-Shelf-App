package com.example.bookshelfapp.ui

import com.example.bookshelfapp.data.model.BookModel

/**
 * Sealed class for user action from [BookScreen]
 */
sealed class BookScreenAction{
    data class OnBookClicked(val bookModel: BookModel): BookScreenAction()
}
