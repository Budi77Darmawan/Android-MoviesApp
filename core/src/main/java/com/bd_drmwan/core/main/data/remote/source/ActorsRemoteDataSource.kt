package com.bd_drmwan.core.main.data.remote.source

import com.bd_drmwan.core.main.data.remote.response.ActorsResponse
import com.bd_drmwan.core.main.services.ActorsService
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class ActorsRemoteDataSource @Inject constructor(
    private val service: ActorsService
): BaseRemoteDataSource() {

    suspend fun getPopularActors(): Flow<Resource<ActorsResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.getPopularCast(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error(errorMsg, errorType))
                }
            }
        }
    }
}