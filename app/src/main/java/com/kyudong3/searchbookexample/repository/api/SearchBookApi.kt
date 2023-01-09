package com.kyudong3.searchbookexample.repository.api

import com.kyudong3.searchbookexample.repository.dao.SearchBookResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface SearchBookApi {

    @GET("v3/search/book")
    fun searchBook(
        @Query("query")
        query: String,
        @Query("sort")      // Not necessary
        sort: String? = null,
        @Query("page")      // Not necessary
        page: Int? = null,
        @Query("size")      // Not necessary
        size: Int? = null,
        @Query("target")    // Not necessary
        target: String? = null
    ): Single<SearchBookResponse>
}
