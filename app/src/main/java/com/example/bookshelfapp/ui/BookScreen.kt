package com.example.bookshelfapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.bookshelfapp.ShowError
import com.example.bookshelfapp.ShowLoading
import com.example.bookshelfapp.data.model.BookModel
import com.example.bookshelfapp.data.model.Response

/**
 * Top level screen that show a Book selected by the user
 */
@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit,
    bookState: Response<BookModel>,
    onEvent: (BookScreenAction) -> Unit
) {
    when(bookState) {
        is Response.Error -> ShowError(error = bookState)
        Response.Loading -> ShowLoading()
        is Response.Success -> {
            BookContent(
                onEvent = onEvent,
                bookModel = bookState.data,
                modifier = modifier
            )
        }
    }

}

@Composable
fun BookContent(
    modifier: Modifier = Modifier,
    onEvent: (BookScreenAction) -> Unit,
    bookModel: BookModel
) {
    Column(
        modifier = modifier
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BookItem(
            bookModel = bookModel,
            onEvent = onEvent,
            modifier = Modifier.height(800.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = bookModel.bookInfo.title,
            style = MaterialTheme.typography.h3,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "by",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = bookModel.bookInfo.author.asDecoupledAuthors(),
            style = MaterialTheme.typography.h5.copy(fontStyle = FontStyle.Italic),
        )
    }

}

fun List<String>.asDecoupledAuthors() = reduce { acc: String, s: String -> acc.plus("; $s") }


fun main() {
    fun List<String>.decoupledAuthors(): String {
        return reduce { acc: String, s: String -> acc.plus("; $s") }
    }

    println(listOf("mark","iker","reyna","nico").decoupledAuthors())
}