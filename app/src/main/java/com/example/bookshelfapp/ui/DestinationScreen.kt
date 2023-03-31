package com.example.bookshelfapp.ui

/**
 * Sealed class for each composable screen
 */
sealed class DestinationScreen(val route: String){
    object BookListScreen: DestinationScreen(route = "books")
    object BookScreen: DestinationScreen(route = "books/")
}
