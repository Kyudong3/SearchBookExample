package com.kyudong3.searchbookexample.di.modules

import androidx.room.Room
import com.kyudong3.searchbookexample.SearchBookApp
import com.kyudong3.searchbookexample.db.dao.BookDocumentDao
import com.kyudong3.searchbookexample.db.database.BookDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object BookDatabaseModule {

    @Singleton
    @Provides
    fun provideBookDatabase(): BookDatabase = Room
        .databaseBuilder(
            SearchBookApp.instance.applicationContext,
            BookDatabase::class.java,
            "book_database.db"
        ).build()

    @Singleton
    @Provides
    fun provideBookDocumentDao(database: BookDatabase): BookDocumentDao =
        database.bookDocumentDao()
}
