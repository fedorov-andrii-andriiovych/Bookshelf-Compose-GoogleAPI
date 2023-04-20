package com.fedorov.andrii.andriiovych.bookshelf.ui

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fedorov.andrii.andriiovych.bookshelf.BooksApplication
import com.fedorov.andrii.andriiovych.bookshelf.data.Book
import com.fedorov.andrii.andriiovych.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface BooksUIState {
    data class Success(val bookSearch: List<Book>) : BooksUIState
    object Loading : BooksUIState
    object Error : BooksUIState
}

class BooksViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    var booksUIState: BooksUIState by mutableStateOf(BooksUIState.Loading)
        private set

    private val _searchWidgetState : MutableState<SearchWidgetState> =
        mutableStateOf( SearchWidgetState.CLOSED)
    val searchWidgetState : State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf("")
    val searchTextState :State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState){
     _searchWidgetState.value = newValue
    }
    fun updateSearchTextState(newValue: String){
        _searchTextState.value = newValue
    }
    init {
        getBooks()
    }

    fun getBooks(query: String = "book", maxResults: Int = 40) {
        viewModelScope.launch {
            booksUIState = BooksUIState.Loading
            booksUIState = try {
                BooksUIState.Success(booksRepository.getBooks(query, maxResults))
            } catch (e: IOException) {
                BooksUIState.Error
            } catch (e: Exception) {
                BooksUIState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksRepository
                BooksViewModel(booksRepository = booksRepository)
            }
        }
    }
}

enum class SearchWidgetState{
    OPENED,
    CLOSED
}