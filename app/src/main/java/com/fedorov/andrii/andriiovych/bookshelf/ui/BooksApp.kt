package com.fedorov.andrii.andriiovych.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedorov.andrii.andriiovych.bookshelf.R
import com.fedorov.andrii.andriiovych.bookshelf.data.Book
import com.fedorov.andrii.andriiovych.bookshelf.ui.screens.HomeScreen

@Composable
fun BooksApp(
    modifier: Modifier = Modifier,
    onBookClicked: (Book) -> Unit
) {
    val booksViewModel: BooksViewModel =
        viewModel(factory = BooksViewModel.Factory)
    val searchWidgetState = booksViewModel.searchWidgetState
    val searchTextState = booksViewModel.searchTextState

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = { booksViewModel.updateSearchTextState(it) },
                onCloseClicked = { booksViewModel.updateSearchWidgetState(SearchWidgetState.CLOSED) },
                onSearchClicked = { booksViewModel.getBooks(it) },
                onSearchTriggered = {
                    booksViewModel.updateSearchWidgetState(SearchWidgetState.OPENED)
                }
            )

        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(
                bookUIState = booksViewModel.booksUIState,
                retryAction = { booksViewModel.getBooks() },
                modifier = modifier,
                onBookClicked
            )
        }
    }
}