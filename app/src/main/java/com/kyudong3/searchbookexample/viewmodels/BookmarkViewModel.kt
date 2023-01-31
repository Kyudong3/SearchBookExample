package com.kyudong3.searchbookexample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toData
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class BookmarkViewModel(
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    private var _bookData = MutableLiveData<List<BookDocument>>(listOf())
    val bookData: LiveData<List<BookDocument>> = _bookData

    private fun getLocalBookDocuments() {
        bookDocumentRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .baseSubscribe {
                val bookList = it.map { entity ->
                    entity.toData()
                }
                _bookData.postValue(bookList)
            }
    }

    fun onResume() {
        getLocalBookDocuments()
    }
}
