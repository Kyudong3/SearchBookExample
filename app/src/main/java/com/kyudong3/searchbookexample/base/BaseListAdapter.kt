package com.kyudong3.searchbookexample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


abstract class BaseListAdapter<T>(
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(diffUtil) {

    abstract val layout: Int

    abstract fun makeViewHolder(binding: ViewDataBinding): BaseViewHolder<T>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return makeViewHolder(binding)
    }

    override fun getItemCount(): Int = currentList.size
}
