package com.kyudong3.searchbookexample.db.repository

import com.kyudong3.searchbookexample.db.dao.BookDocumentDao
import com.kyudong3.searchbookexample.db.entity.BookDocument
import kotlinx.coroutines.flow.Flow


class BookDocumentRepositoryImpl(
    private val bookDocumentDao: BookDocumentDao
) : BookDocumentRepository {

    override fun getAll(): Flow<List<BookDocument>> = bookDocumentDao.getAll()

    override suspend fun insertBookDocument(item: BookDocument) =
        bookDocumentDao.insertBookDocument(item)

    override suspend fun deleteBookDocument(item: BookDocument) =
        bookDocumentDao.deleteBookDocument(item)
}
