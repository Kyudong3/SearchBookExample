package com.kyudong3.searchbookexample.repository.dao

import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.dto.BookMeta


data class SearchBookResponse(
    val meta: BookMeta,
    val Documents: List<BookDocument>
)
