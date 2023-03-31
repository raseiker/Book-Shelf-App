package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.BookResponse

/**
 *  Top level screen that show a collection of books retrieved from remote repository
 */
@Composable
fun BookListScreen(
    modifier: Modifier = Modifier,
    bookResponse: BookResponse,
    onEvent: (BookScreenAction) -> Unit,
    navigateToBookItem: (id: String) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(bookResponse.items, key = {it.id}){bookModel ->
            BookItem(
                bookModel = bookModel,
                onEvent = onEvent,
                navigateToBookItem = navigateToBookItem,
                modifier = Modifier.height(300.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BookItem(
    modifier: Modifier = Modifier,
    bookModel: BookModel,
    onEvent: (BookScreenAction) -> Unit,
    navigateToBookItem: ((id: String) -> Unit)? = null
) {
    Surface(
        modifier = modifier,
        elevation = 10.dp,
        onClick = {
            navigateToBookItem?.let {
                onEvent(BookScreenAction.OnBookClicked(bookModel))
                it(bookModel.id)
            }
        }
    ) {
        AsyncImage(
            model = bookModel.bookInfo.bookImage.image.replace("http", "https"),
            contentDescription = bookModel.bookInfo.title,
            contentScale = ContentScale.FillBounds
        )
    }
}