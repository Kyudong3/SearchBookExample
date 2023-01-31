package com.kyudong3.searchbookexample.data.mapper

import com.kyudong3.searchbookexample.data.dto.BookDocument


internal fun BookDocument.toEntity() = com.kyudong3.searchbookexample.db.entity.BookDocument(
    title = title,
    contents = contents,
    url = url,
    publisher = publisher,
    price = price,
    thumbnail = thumbnail,
    status = status
)