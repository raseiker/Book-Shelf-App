package com.example.bookshelfapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bookshelfapp.data.model.*
import com.example.bookshelfapp.ui.BookApp
import com.example.bookshelfapp.ui.BookScreenAction
import com.example.bookshelfapp.ui.theme.BookShelfAppTheme
import com.example.bookshelfapp.ui.viewmodel.BookViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookShelfAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val bookViewModel = viewModel<BookViewModel>()
                    when (val res = bookViewModel.bookListState){
                        is Response.Success -> {
                            BookApp(
                                bookResponse = res.data,
                                onEvent = bookViewModel::onEvent,
                                bookState = bookViewModel.bookState
                            )
                        }
                        is Response.Error -> {
                            ShowError(error = res)
                        }
                        Response.Loading -> {
                            ShowLoading()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowLoading(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
fun ShowError(
    modifier: Modifier = Modifier,
    error: Response.Error
) {
    Snackbar(modifier = modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.BottomCenter)
    ) {
        Text(text = error.message ?: "Unknown error")
    }
}