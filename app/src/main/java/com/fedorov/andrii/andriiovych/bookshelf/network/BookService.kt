package com.fedorov.andrii.andriiovych.bookshelf.network

import com.fedorov.andrii.andriiovych.bookshelf.network.model.BookShelf
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("volumes")
    suspend fun bookSearch(
        @Query("q") searchQuery: String,
        @Query("maxResults") maxResults: Int
    ):BookShelf
}