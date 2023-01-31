package com.kyudong3.searchbookexample.utils.provider


interface ResourceProvider {
    fun getString(resId: Int): String
}
