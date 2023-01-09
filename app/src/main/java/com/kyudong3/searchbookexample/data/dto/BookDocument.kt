package com.kyudong3.searchbookexample.data.dto

import com.google.gson.annotations.SerializedName
import java.util.*


data class BookDocument(
    val title: String,      // 글 제목
    val contents: String,   // 글 요약
    val url: String,        // 글 URL
    @SerializedName("blogname")
    val blogName: String,   // 블로그 이름
    val thumbnail: String,  // 검색 시스템에서 추출한 대표 미리보기 이미지 URL
    val dateTime: Date      // 블로그 글 작성시간, ISO 8601, [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
)
