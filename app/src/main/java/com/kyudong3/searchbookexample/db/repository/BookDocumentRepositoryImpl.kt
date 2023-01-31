package com.kyudong3.searchbookexample.db.repository

import com.kyudong3.searchbookexample.db.dao.BookDocumentDao
import com.kyudong3.searchbookexample.db.entity.BookDocument
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single


class BookDocumentRepositoryImpl(
    private val bookDocumentDao: BookDocumentDao
) : BookDocumentRepository {

    override fun getAll(): Single<List<BookDocument>> = bookDocumentDao.getAll()

    override fun insertBookDocument(item: BookDocument): Completable =
        bookDocumentDao.insertBookDocument(item)

    override fun deleteBookDocument(item: BookDocument): Completable =
        bookDocumentDao.deleteBookDocument(item)
}
