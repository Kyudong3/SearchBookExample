package com.kyudong3.searchbookexample.data.dto


data class BookMeta(
    val totalCount: Int,    // 검색된 문서 수
    val pageableCount: Int, // total_count 중 노출 가능 문서 수
    val isEnd: Boolean      // 현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음
)
