package com.kyudong3.searchbookexample.viewmodels

import androidx.lifecycle.viewModelScope
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.data.mapper.toData
import com.kyudong3.searchbookexample.data.mapper.toEntity
import com.kyudong3.searchbookexample.db.repository.BookDocumentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class BookmarkViewModel(
    private val bookDocumentRepository: BookDocumentRepository
) : BaseViewModel() {

    private var _bookData: MutableStateFlow<List<BookDocument>> = MutableStateFlow(emptyList())
    val bookData: StateFlow<List<BookDocument>> = _bookData

    init {
        getLocalBookDocuments()
    }

    private fun getLocalBookDocuments() {
        viewModelScope.launch {
            bookDocumentRepository
                .getAll()
                .catch { /* FIXME: 에러처리 추가 */ }
                .collect {
                    val bookList = it.map { entity ->
                        entity.toData()
                    }
                    _bookData.value = bookList
                }
        }
    }

    fun onClickBookmark(bookDocument: BookDocument) {
        viewModelScope.launch {
            bookDocumentRepository.deleteBookDocument(
                bookDocument.copy(favorite = !bookDocument.favorite).toEntity()
            )
        }
    }
}
