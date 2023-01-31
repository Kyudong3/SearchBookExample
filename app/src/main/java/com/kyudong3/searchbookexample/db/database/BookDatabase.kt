package com.kyudong3.searchbookexample.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kyudong3.searchbookexample.db.dao.BookDocumentDao
import com.kyudong3.searchbookexample.db.entity.BookDocument


@Database(entities = [BookDocument::class], version = 1, exportSchema = false)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDocumentDao(): BookDocumentDao
}
