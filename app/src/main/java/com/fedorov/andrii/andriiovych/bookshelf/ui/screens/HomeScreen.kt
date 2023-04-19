package com.fedorov.andrii.andriiovych.bookshelf.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fedorov.andrii.andriiovych.bookshelf.ui.BooksUIState

@Composable
fun HomeScreen(
    bookUIState:BooksUIState,
    retryAction: () -> Unit,
    modifier: Modifier
){
    when(bookUIState){
        is BooksUIState.Loading -> LoadingScreen(modifier = modifier)
        is BooksUIState.Success -> BooksGridScreen(
            books = bookUIState.bookSearch,
            modifier = modifier)
        is BooksUIState.Error -> ErrorScreen(retryAction = retryAction,modifier) 
    }
}