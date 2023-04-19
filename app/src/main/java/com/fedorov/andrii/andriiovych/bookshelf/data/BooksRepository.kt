package com.fedorov.andrii.andriiovych.bookshelf.data

import com.fedorov.andrii.andriiovych.bookshelf.network.BookService

interface BooksRepository {

    suspend fun getBooks(query:String,maxResults:Int):List<Book>
}

class NetworkBooksRepository(
    private var bookService :BookService
):BooksRepository{
    override suspend fun getBooks(query: String, maxResults: Int):List<Book> =
        bookService.bookSearch(query,maxResults).items.map { items->
            Book(
                title = items.volumeInfo.title,
                previewLink = items.volumeInfo.previewLink,
                imageLink = items.volumeInfo.imageLinks.thumbnail
            ) }

}