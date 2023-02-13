package com.kyudong3.searchbookexample.data.dto


data class BookDocument(
    val title: String,      // 도서 제목
    val contents: String,   // 도서 소개
    val url: String,        // 도서 상세 URL
    val publisher: String,  // 도서 출판사
    val price: Int,         // 도서 판매가
    val thumbnail: String,  // 도서 표지 미리보기 URL
    val status: String,     // 도서 판매 상태 정보 (정상, 품절, 절판 등)
    var favorite: Boolean = false
) {

}
