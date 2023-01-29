package com.kyudong3.searchbookexample.ui.widget.recyclerview.viewholder

import android.view.View
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseViewHolder
import com.kyudong3.searchbookexample.data.dto.BookDocument


class SearchBookViewHolder(
    val binding: ViewDataBinding,
    private val itemClickListener: ((View, BookDocument) -> Unit)?
) : BaseViewHolder<BookDocument>(binding) {

    override fun bind(item: BookDocument) {
        super.bind(item)

        val favorite = binding.root.findViewById<ImageView>(R.id.iv_favorite)
        favorite.setOnClickListener {
            itemClickListener?.invoke(it, item)
        }
    }

    companion object {
        fun newInstance(
            binding: ViewDataBinding,
            itemClickListener: ((View, BookDocument) -> Unit)?
        ) = SearchBookViewHolder(binding, itemClickListener)
    }
}
