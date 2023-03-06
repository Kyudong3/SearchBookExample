package com.kyudong3.searchbookexample.db.repository

import com.kyudong3.searchbookexample.db.entity.BookDocument
import kotlinx.coroutines.flow.Flow


interface BookDocumentRepository {
    fun getAll(): Flow<List<BookDocument>>
    suspend fun insertBookDocument(item: BookDocument)
    suspend fun deleteBookDocument(item: BookDocument)
}
