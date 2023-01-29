package com.kyudong3.searchbookexample.data.dto

import java.util.*


data class BookDocument(
    val title: String,      // 도서 제목
    val contents: String,   // 도서 소개
    val url: String,        // 도서 상세 URL
    val authors: List<String>, // 도서 저자 리스트
    val publisher: String,  // 도서 출판사
    val price: Int,         // 도서 판매가
    val thumbnail: String,  // 도서 표지 미리보기 URL
    val dateTime: Date,     // 도서 출판날짜, ISO 8601 형식 [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
    val status: String      // 도서 판매 상태 정보 (정상, 품절, 절판 등)
)
