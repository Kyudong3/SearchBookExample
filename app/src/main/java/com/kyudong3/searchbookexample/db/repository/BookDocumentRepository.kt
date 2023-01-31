package com.kyudong3.searchbookexample.db.repository

import com.kyudong3.searchbookexample.db.entity.BookDocument
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


interface BookDocumentRepository {
    fun getAll(): Single<List<BookDocument>>
    fun insertBookDocument(item: BookDocument): Completable
    fun deleteBookDocument(item: BookDocument): Completable
}
