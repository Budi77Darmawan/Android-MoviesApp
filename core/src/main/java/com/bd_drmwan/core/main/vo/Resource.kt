package com.bd_drmwan.core.main.vo

import com.bd_drmwan.core.main.data.remote.response.ErrorTypeEnum

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T?) : Resource<T>()

    data class Error<T>(
        val message: String,
        val errorType: ErrorTypeEnum = ErrorTypeEnum.BASIC
    ) : Resource<T>()
}