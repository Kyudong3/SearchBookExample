package com.kyudong3.searchbookexample.viewmodels

import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.repository.SearchBookRepository


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository
) : BaseViewModel() {

    var bookName: String = ""

    init {
        bookName = "개발 7년차, 매니저 1일차"
    }
}
