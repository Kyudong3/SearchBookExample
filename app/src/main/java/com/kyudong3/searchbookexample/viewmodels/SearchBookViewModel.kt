package com.kyudong3.searchbookexample.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toEntity
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.repository.SearchBookRepository
import io.reactivex.rxjava3.schedulers.Schedulers


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository,
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    private var _bookData = MutableLiveData<List<BookDocument>>(listOf())
    val bookData: LiveData<List<BookDocument>> = _bookData

    fun onClickSearch() {
        searchBookRepository
            .searchBook(searchQuery)
            .subscribeOn(Schedulers.io())
            .baseSubscribe {
                if (it.documents.isNotEmpty()) {
                    _bookData.postValue(it.documents)
                }
            }
    }

    fun onClickBookmark(bookDocument: BookDocument) {
        bookDocumentRepository
            .insertBookDocument(bookDocument.toEntity())
            .subscribeOn(Schedulers.io())
            .baseSubscribe { }
    }
}
