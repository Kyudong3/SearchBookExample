package com.kyudong3.searchbookexample.ui.widget.recyclerview.viewholder

import androidx.databinding.ViewDataBinding
import com.kyudong3.searchbookexample.base.BaseViewHolder
import com.kyudong3.searchbookexample.data.dto.BookDocument


class SearchBookViewHolder(
    val binding: ViewDataBinding
) : BaseViewHolder<BookDocument>(binding) {

    override fun bind(item: BookDocument) {
        super.bind(item)
    }

    companion object {
        fun newInstance(binding: ViewDataBinding) = SearchBookViewHolder(binding)
    }
}
