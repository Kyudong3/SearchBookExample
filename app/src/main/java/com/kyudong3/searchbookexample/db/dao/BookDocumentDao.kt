package com.kyudong3.searchbookexample.db.dao

import androidx.room.*
import com.kyudong3.searchbookexample.db.entity.BookDocument
import kotlinx.coroutines.flow.Flow


@Dao
interface BookDocumentDao {

    @Query("SELECT * FROM book_document")
    fun getAll(): Flow<List<BookDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookDocument(item: BookDocument)

    @Delete
    suspend fun deleteBookDocument(item: BookDocument)
}
