package com.example.bookshelfapp.di

import com.example.bookshelfapp.data.repository.BookRepository
import com.example.bookshelfapp.data.repository.BookRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        impl: BookRepositoryImpl
    ): BookRepository
}