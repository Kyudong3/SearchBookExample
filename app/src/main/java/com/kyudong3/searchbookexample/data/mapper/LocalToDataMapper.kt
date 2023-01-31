package com.kyudong3.searchbookexample.data.mapper

import com.kyudong3.searchbookexample.data.dto.BookDocument


internal fun com.kyudong3.searchbookexample.db.entity.BookDocument.toData() = BookDocument(
    title = title,
    contents = contents,
    url = url,
    publisher = publisher,
    price = price,
    thumbnail = thumbnail,
    status = status
)