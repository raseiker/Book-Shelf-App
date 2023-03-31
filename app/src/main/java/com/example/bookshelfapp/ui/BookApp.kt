package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse
import com.example.bookshelfapp.data.model.Response

@Composable
fun BookApp(
    modifier: Modifier = Modifier,
    bookResponse: BookResponse,
    onEvent: (BookScreenAction) -> Unit,
    bookState: Response<BookModel>
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BookNavigation(
            bookResponse = bookResponse,
            modifier = Modifier.padding(it),
            onEvent = onEvent,
            bookState = bookState
        )
    }
}

/**
 * NAvHost for navigation app
 */
@Composable
fun BookNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    bookResponse: BookResponse,
    onEvent: (BookScreenAction) -> Unit,
    bookState: Response<BookModel>
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = DestinationScreen.BookListScreen.route
    ){
        composable(route = DestinationScreen.BookListScreen.route){
            BookListScreen(
                bookResponse = bookResponse,
                onEvent = onEvent,
                navigateToBookItem = { ibBook -> navController.navigate(DestinationScreen.BookScreen.route + ibBook) }
            )
        }
        composable(
            route = DestinationScreen.BookScreen.route + "{idBook}",
            arguments = listOf(
                navArgument(name = "idBook"){
                    type = NavType.StringType
                }
            )
        ){
            BookScreen(
                navigateUp = { navController.navigateUp() },
                bookState = bookState,
                onEvent = onEvent
            )
        }
    }

}