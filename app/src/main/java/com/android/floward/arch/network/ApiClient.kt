package com.android.floward.arch.network

interface ApiClient {

    fun <T> getService(cls: Class<T>): T
}