package com.kyudong3.searchbookexample.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


abstract class BaseListAdapter<T>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(diffUtil) {

    abstract val layout: Int

    private var itemClickListener: ((View, T) -> Unit)? = null

    fun setItemClickListener(itemClickListener: (View, T) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    abstract fun makeViewHolder(
        binding: ViewDataBinding,
        itemClickListener: ((View, T) -> Unit)?
    ): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return makeViewHolder(binding, itemClickListener)
    }

    override fun getItemCount(): Int = currentList.size
}
