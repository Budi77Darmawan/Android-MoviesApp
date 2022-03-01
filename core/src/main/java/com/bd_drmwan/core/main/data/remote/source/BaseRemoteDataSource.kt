package com.bd_drmwan.core.main.data.remote.source

import com.bd_drmwan.core.BuildConfig.API_KEY
import com.bd_drmwan.core.main.data.remote.response.ErrorTypeEnum
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseRemoteDataSource {
    protected val apiKey = API_KEY
    protected val language = "en-US"

    suspend fun <T> validateResponse(
        response: Response<T>,
        onSuccess: suspend (result: T) -> Unit,
        onError: suspend (message: String) -> Unit
    ) {
        if (response.isSuccessful) {
            response.body()?.let {
                onSuccess(it)
            } ?: run { onError("Data is null") }
        } else {
            onError("Internal server error")
        }
    }

    suspend fun validateError(
        error: Throwable,
        messageError: suspend (String, ErrorTypeEnum) -> Unit
    ) {
        when (error) {
            is ConnectException -> messageError(error.localizedMessage ?:"Internal server error", ErrorTypeEnum.NO_CONNECTION)
            is SocketTimeoutException -> messageError("Socket timeout", ErrorTypeEnum.TIMEOUT)
            is IOException -> messageError("Connection timeout", ErrorTypeEnum.TIMEOUT)
            else -> messageError(error.localizedMessage ?:"Internal server error", ErrorTypeEnum.BASIC)
        }
    }
}