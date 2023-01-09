package com.kyudong3.searchbookexample.utils.provider.impl

import com.kyudong3.searchbookexample.utils.provider.ApiProvider
import retrofit2.Retrofit


class ApiProviderImpl(
    private val retrofit: Retrofit
) : ApiProvider {

    override fun <T> get(clazz: Class<T>): T = retrofit.create(clazz)
}
