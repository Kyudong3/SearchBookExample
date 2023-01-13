package com.kyudong3.searchbookexample.viewmodels

import android.util.Log
import androidx.databinding.Bindable
import com.kyudong3.searchbookexample.base.BaseViewModel
import com.kyudong3.searchbookexample.repository.SearchBookRepository


class SearchBookViewModel(
    private val searchBookRepository: SearchBookRepository
) : BaseViewModel() {

    @get:Bindable
    var searchQuery: String = ""

    fun onClickSearch() {
        Log.e("ddd", searchQuery)
    }
}
