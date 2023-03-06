package com.kyudong3.searchbookexample.repository

import com.kyudong3.searchbookexample.data.dto.BookDocument
import kotlinx.coroutines.flow.Flow


interface SearchBookRepository {

    fun searchBook(query: String): Flow<List<BookDocument>>
}
