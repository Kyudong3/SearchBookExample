package com.kyudong3.searchbookexample.viewmodels

import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toData
import com.kyudong3.searchbookexample.data.mapper.toEntity
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import com.kyudong3.searchbookexample.repository.SearchBookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository,
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    private var _bookData: MutableStateFlow<List<BookDocument>> = MutableStateFlow(emptyList())
    val bookData: StateFlow<List<BookDocument>> = _bookData

    private var _localBookData: MutableStateFlow<List<BookDocument>> = MutableStateFlow(emptyList())
    val localBookData: StateFlow<List<BookDocument>> = _localBookData

    init {
        getLocalBookmarkList()
    }

    fun onClickSearch() {
        viewModelScope.launch {
            searchBookRepository
                .searchBook(searchQuery)
                .catch { /* FIXME : Exception 추가 */ }
                .collect { bookDocuments ->
                    _localBookData.value.let { localDocuments ->
                        if (localDocuments.isEmpty())
                            _bookData.value = bookDocuments
                        else
                            _bookData.value = getSyncedDocuments(bookDocuments, localDocuments)
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
        viewModelScope.launch {
            bookDocumentRepository
                .getAll()
                .catch { }
                .collect { localDocuments ->
                    val localDocumentList = localDocuments.map { entity ->
                        entity.toData()
                    }

                    val diff = _localBookData.value.filterNot { localDocument ->
                        localDocumentList.contains(localDocument)
                    }

                    if (diff.isNotEmpty()) {
                        _bookData.value.let { bookDocuments ->
                            if (bookDocuments.isNotEmpty()) {
                                _bookData.value = getSyncedDocuments(
                                    bookDocuments,
                                    localDocumentList,
                                    diff.first()
                                )
                            }
                        }
                    }

                    _localBookData.value = localDocumentList
                }
        }
    }

    fun onClickBookmark(bookDocument: BookDocument) {
        if (bookDocument.favorite)
            deleteBookDocument(bookDocument)
        else
            insertBookDocument(bookDocument)
    }

    private fun deleteBookDocument(bookDocument: BookDocument) {
        viewModelScope.launch {
            val copy = bookDocument.copy(favorite = false)

            bookDocumentRepository.deleteBookDocument(copy.toEntity())
            updateDocument(copy)
        }
    }

    private fun insertBookDocument(bookDocument: BookDocument) {
        viewModelScope.launch {
            val copy = bookDocument.copy(favorite = true)

            bookDocumentRepository.insertBookDocument(copy.toEntity())
            updateDocument(copy)
        }
    }

    private fun updateDocument(copy: BookDocument) {
        val documentList = arrayListOf<BookDocument>()

        _bookData.value.forEach {
            if (it.url == copy.url)
                documentList.add(copy)
            else
                documentList.add(it)
        }

        _bookData.value = documentList
    }
}
