package com.fedorov.andrii.andriiovych.bookshelf

import android.app.Application
import com.fedorov.andrii.andriiovych.bookshelf.data.AppContainer
import com.fedorov.andrii.andriiovych.bookshelf.data.DefaultAppContainer

class BooksApplication:Application() {
    lateinit var container:AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}