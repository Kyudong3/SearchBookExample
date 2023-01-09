package com.kyudong3.searchbookexample.utils.provider


interface ApiProvider {

    operator fun <T> get(clazz: Class<T>): T
}
