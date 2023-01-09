package com.kyudong3.searchbookexample.repository

import com.kyudong3.searchbookexample.repository.dao.SearchBookResponse
import io.reactivex.rxjava3.core.Single


interface SearchBookRepository {

    fun searchBook(query: String): Single<SearchBookResponse>
}
