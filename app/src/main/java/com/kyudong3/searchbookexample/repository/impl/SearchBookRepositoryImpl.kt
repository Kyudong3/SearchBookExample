package com.kyudong3.searchbookexample.repository.impl

import com.kyudong3.searchbookexample.repository.SearchBookRepository
import com.kyudong3.searchbookexample.repository.api.SearchBookApi
import com.kyudong3.searchbookexample.repository.dao.SearchBookResponse
import com.kyudong3.searchbookexample.utils.provider.ApiProvider
import io.reactivex.rxjava3.core.Single


class SearchBookRepositoryImpl(
    apiProvider: ApiProvider
) : SearchBookRepository {

    private val api = apiProvider[SearchBookApi::class.java]

    override fun searchBook(query: String): Single<SearchBookResponse> =
        api.searchBook(query)
}
