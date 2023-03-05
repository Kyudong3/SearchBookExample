package com.kyudong3.searchbookexample.db.repository

import com.kyudong3.searchbookexample.db.entity.BookDocument
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


interface BookDocumentRepository {
    fun getAll(): Flowable<List<BookDocument>>
    fun insertBookDocument(item: BookDocument): Completable
    fun deleteBookDocument(item: BookDocument): Completable
}
