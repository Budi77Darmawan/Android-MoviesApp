package com.bd_drmwan.core.main.data.remote.source

import com.bd_drmwan.core.main.data.remote.response.CastResponse
import com.bd_drmwan.core.main.services.CastService
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CastRemoteDataSource @Inject constructor(
    private val service: CastService
): BaseRemoteDataSource() {

    suspend fun getPopularActors(): Flow<Resource<CastResponse>> {
        return flow {
            emit(Resource.Loading<CastResponse>())
            try {
                val response = service.getPopularCast(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<CastResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<CastResponse>(errorMsg, errorType))
                }
            }
        }
    }
}