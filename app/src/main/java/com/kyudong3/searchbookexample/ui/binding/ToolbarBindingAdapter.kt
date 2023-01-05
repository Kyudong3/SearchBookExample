package com.kyudong3.searchbookexample.ui.binding

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.kyudong3.searchbookexample.utils.getActivity


@BindingAdapter("supportActionBar")
fun Toolbar.setSupportActionBar(
    supportActionBar: Boolean
) {
    if (!supportActionBar) return

    (this.context.getActivity() as AppCompatActivity).let {
        it.setSupportActionBar(this)
        it.supportActionBar?.setDisplayShowTitleEnabled(false)
    }
}