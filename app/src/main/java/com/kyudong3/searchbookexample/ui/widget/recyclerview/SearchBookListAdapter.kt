package com.kyudong3.searchbookexample.ui.widget.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseListAdapter
import com.kyudong3.searchbookexample.base.BaseViewHolder
import com.kyudong3.searchbookexample.data.dto.BookDocument
import com.kyudong3.searchbookexample.ui.widget.recyclerview.viewholder.SearchBookViewHolder


class SearchBookListAdapter : BaseListAdapter<BookDocument>(diffUtil) {

    override val layout: Int = R.layout.item_book

    override fun makeViewHolder(binding: ViewDataBinding): BaseViewHolder<BookDocument> =
        SearchBookViewHolder.newInstance(binding)

    override fun onBindViewHolder(holder: BaseViewHolder<BookDocument>, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<BookDocument>() {
            override fun areItemsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: BookDocument, newItem: BookDocument): Boolean {
                return oldItem == newItem
            }
        }
    }
}
