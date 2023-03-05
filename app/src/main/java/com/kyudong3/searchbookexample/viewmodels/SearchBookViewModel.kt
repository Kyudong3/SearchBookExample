package com.kyudong3.searchbookexample.viewmodels

import androidx.databinding.Bindable
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.base.SingleLiveEvent
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toEntity
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.repository.SearchBookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository,
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    val bookData = SingleLiveEvent<List<BookDocument>>()

    fun onClickSearch() {
        searchBookRepository
            .searchBook(searchQuery)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { Single.just(it.documents) }
            .baseSubscribe {
                bookData.value = it
            }
    }

    fun onClickBookmark(bookDocument: BookDocument) {
        bookDocumentRepository
            .insertBookDocument(
                bookDocument.copy(favorite = !bookDocument.favorite).toEntity()
            )
            .subscribeOn(Schedulers.io())
            .baseSubscribe { }
    }
}
