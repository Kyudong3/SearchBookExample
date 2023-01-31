package com.kyudong3.searchbookexample.utils.provider.impl

import android.content.Context
import com.kyudong3.searchbookexample.utils.provider.ResourceProvider


class ResourceProviderImpl(
    private val context: Context
) : ResourceProvider {

    override fun getString(resId: Int) =
        context.getString(resId)
}
