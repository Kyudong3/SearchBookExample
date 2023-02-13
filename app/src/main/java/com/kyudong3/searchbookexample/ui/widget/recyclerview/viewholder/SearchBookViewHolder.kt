package com.kyudong3.searchbookexample.ui.widget.recyclerview.viewholder

import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseViewHolder
import com.kyudong3.searchbookexample.data.dto.BookDocument


class SearchBookViewHolder(
    val binding: ViewDataBinding,
    private val itemClickListener: ((BookDocument, Int) -> Unit)?
) : BaseViewHolder<BookDocument>(binding) {

    override fun bind(item: BookDocument) {
        super.bind(item)

        val favorite = binding.root.findViewById<ImageView>(R.id.iv_favorite)
        favorite.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION) {
                item.favorite = !item.favorite
                itemClickListener?.invoke(item, adapterPosition)
            }
        }
    }

    companion object {
        fun newInstance(
            binding: ViewDataBinding,
            itemClickListener: ((BookDocument, Int) -> Unit)?
        ) = SearchBookViewHolder(binding, itemClickListener)
    }
}
