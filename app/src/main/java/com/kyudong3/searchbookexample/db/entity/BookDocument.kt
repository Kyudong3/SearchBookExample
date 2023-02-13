package com.kyudong3.searchbookexample.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "book_document")
data class BookDocument(
    @PrimaryKey
    @ColumnInfo(name = "url")
    val url: String,        // 도서 상세 URL
    @ColumnInfo(name = "title")
    val title: String,      // 도서 제목
    @ColumnInfo(name = "contents")
    val contents: String,   // 도서 소개
    @ColumnInfo(name = "publisher")
    val publisher: String,  // 도서 출판사
    @ColumnInfo(name = "price")
    val price: Int,         // 도서 판매가
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,  // 도서 표지 미리보기 URL
    @ColumnInfo(name = "status")
    val status: String,     // 도서 판매 상태 정보 (정상, 품절, 절판 등)
    @ColumnInfo(name = "favorite")
    val favorite: Boolean
)
