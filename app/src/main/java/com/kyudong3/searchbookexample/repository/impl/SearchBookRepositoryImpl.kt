package com.kyudong3.searchbookexample.repository.impl

import com.kyudong3.searchbookexample.repository.SearchBookRepository
import com.kyudong3.searchbookexample.repository.api.SearchBookApi
import com.kyudong3.searchbookexample.utils.provider.ApiProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class SearchBookRepositoryImpl(
    apiProvider: ApiProvider
) : SearchBookRepository {

    private val api = apiProvider[SearchBookApi::class.java]

    override fun searchBook(query: String) = flow {
        val response = api.searchBook(query)
        if (response.isSuccessful)
            emit(response.body()?.documents ?: emptyList())
    }.flowOn(Dispatchers.IO)
}
