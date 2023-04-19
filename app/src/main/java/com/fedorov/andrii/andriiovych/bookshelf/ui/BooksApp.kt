package com.fedorov.andrii.andriiovych.bookshelf.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fedorov.andrii.andriiovych.bookshelf.R
import com.fedorov.andrii.andriiovych.bookshelf.ui.screens.HomeScreen

@Composable
fun BooksApp(
    modifier: Modifier = Modifier
){
    val booksViewModel:BooksViewModel =
        viewModel(factory = BooksViewModel.Factory)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title ={Text(stringResource(id = R.string.app_name)) })}
    ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
            color = MaterialTheme.colors.background 
            ) {
                HomeScreen(
                    bookUIState = booksViewModel.booksUIState ,
                    retryAction = { booksViewModel::getBooks},
                    modifier = modifier )
            }
    }
}