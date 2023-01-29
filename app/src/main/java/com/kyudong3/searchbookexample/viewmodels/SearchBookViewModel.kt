package com.kyudong3.searchbookexample.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.repository.SearchBookRepository


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    private var _bookData = MutableLiveData<List<BookDocument>>(listOf())
    val bookData: LiveData<List<BookDocument>> = _bookData

    fun onClickSearch() {
        searchBookRepository
            .searchBook(searchQuery)
            .baseSubscribe {
                if (it.documents.isNotEmpty()) {
                    _bookData.postValue(it.documents)
                }
            }
    }
}
