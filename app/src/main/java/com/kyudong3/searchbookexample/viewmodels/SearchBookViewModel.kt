package com.kyudong3.searchbookexample.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.base.SingleLiveEvent
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toData
import com.kyudong3.searchbookexample.data.mapper.toEntity
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.repository.SearchBookRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository,
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    val bookData = SingleLiveEvent<List<BookDocument>>()

    private var _localBookData = MutableLiveData<List<BookDocument>>(listOf())
    val localBookData: LiveData<List<BookDocument>> = _localBookData

    init {
        getLocalBookmarkList()
    }

    fun onClickSearch() {
        viewModelScope.launch {
            searchBookRepository
                .searchBook(searchQuery)
                .catch { /* FIXME : Exception 추가 */ }
                .collect { bookDocuments ->
                    localBookData.value?.let { localDocuments ->
                        if (localDocuments.isEmpty())
                            bookData.value = bookDocuments
                        else
                            bookData.value = getSyncedDocuments(bookDocuments, localDocuments)
                    }
                }
        }
    }

    private fun getSyncedDocuments(
        bookDocuments: List<BookDocument>,
        localDocuments: List<BookDocument>,
        diffDocument: BookDocument? = null
    ): List<BookDocument> {
        val documentList = arrayListOf<BookDocument>()

        bookDocuments.forEach { bookDocument ->
            val found = localDocuments.find { localDocument ->
                bookDocument.url == localDocument.url
            }

            found?.let {
                documentList.add(bookDocument.copy(favorite = it.favorite))
            } ?: run {
                when {
                    diffDocument == null ->
                        documentList.add(bookDocument)
                    bookDocument.url == diffDocument.url ->
                        documentList.add(diffDocument.copy(favorite = !diffDocument.favorite))
                    else ->
                        documentList.add(bookDocument)
                }
            }
        }

        return documentList
    }

    private fun getLocalBookmarkList() {
        bookDocumentRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .baseSubscribe { localDocuments ->
                val localDocumentList = localDocuments.map { entity ->
                    entity.toData()
                }

                _localBookData.value?.let {
                    val diff = it.filterNot { localDocument ->
                        localDocumentList.contains(localDocument)
                    }

                    if (diff.isNotEmpty()) {
                        bookData.value?.let { bookDocuments ->
                            if (bookDocuments.isNotEmpty()) {
                                bookData.value = getSyncedDocuments(
                                    bookDocuments,
                                    localDocumentList,
                                    diff.first()
                                )
                            }
                        }
                    }
                }

                _localBookData.value = localDocumentList
            }
    }

    fun onClickBookmark(bookDocument: BookDocument) {
        if (bookDocument.favorite)
            deleteBookDocument(bookDocument)
        else
            insertBookDocument(bookDocument)
    }

    private fun deleteBookDocument(bookDocument: BookDocument) {
        val copy = bookDocument.copy(favorite = false)
        bookDocumentRepository
            .deleteBookDocument(copy.toEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .baseSubscribe {
                updateDocument(copy)
            }
    }

    private fun insertBookDocument(bookDocument: BookDocument) {
        val copy = bookDocument.copy(favorite = true)
        bookDocumentRepository
            .insertBookDocument(copy.toEntity())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .baseSubscribe {
                updateDocument(copy)
            }
    }

    private fun updateDocument(copy: BookDocument) {
        val documentList = arrayListOf<BookDocument>()

        bookData.value?.let { bookDocuments ->
            bookDocuments.forEach {
                if (it.url == copy.url)
                    documentList.add(copy)
                else
                    documentList.add(it)
            }

            bookData.value = documentList
        }
    }
}
