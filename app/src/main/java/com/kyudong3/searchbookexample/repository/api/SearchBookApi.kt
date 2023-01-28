package com.kyudong3.searchbookexample.repository.api

import com.kyudong3.searchbookexample.BuildConfig
import com.kyudong3.searchbookexample.repository.dao.SearchBookResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface SearchBookApi {

    @GET("v3/search/book")
    fun searchBook(
        @Query("query")
        query: String,
        @Query("sort")      // 결과 문서 정렬 방식, accuracy(정확도순) 또는 latest(발간일순), 기본값 accuracy
        sort: String? = null,
        @Query("page")      // 결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        page: Int? = null,
        @Query("size")      // 한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10
        size: Int? = null,
        @Query("target")    // 검색 필드 제한
        target: String? = null,
        @Header("Authorization")
        apiKey: String? = "KakaoAK ${BuildConfig.KAKAO_API_KEY}"
    ): Single<SearchBookResponse>
}
