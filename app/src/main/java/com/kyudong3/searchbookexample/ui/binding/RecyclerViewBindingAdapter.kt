package com.kyudong3.searchbookexample.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("listAdapter")
fun RecyclerView.setRecyclerView(
    adapter: RecyclerView.Adapter<*>?
) {
    if (this.adapter != adapter)
        this.adapter = adapter
}
