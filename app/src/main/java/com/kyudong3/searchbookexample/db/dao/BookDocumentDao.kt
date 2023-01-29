package com.kyudong3.searchbookexample.db.dao

import androidx.room.*
import com.kyudong3.searchbookexample.db.entity.BookDocument
import io.reactivex.rxjava3.core.Single


@Dao
interface BookDocumentDao {

    @Query("SELECT * FROM book_document")
    fun getAll(): Single<List<BookDocument>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBookDocument(item: BookDocument)

    @Delete
    fun deleteBookDocument(item: BookDocument)

    @Update
    fun updateBookDocument(item: BookDocument)
}
