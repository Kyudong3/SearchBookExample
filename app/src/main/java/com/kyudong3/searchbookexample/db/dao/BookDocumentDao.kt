package com.kyudong3.searchbookexample.db.dao

import androidx.room.*
import com.kyudong3.searchbookexample.db.entity.BookDocument
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


@Dao
interface BookDocumentDao {

    @Query("SELECT * FROM book_document")
    fun getAll(): Single<List<BookDocument>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookDocument(item: BookDocument): Completable

    @Delete
    fun deleteBookDocument(item: BookDocument): Completable
}
