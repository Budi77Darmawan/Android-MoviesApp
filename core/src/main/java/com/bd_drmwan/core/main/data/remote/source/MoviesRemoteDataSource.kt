package com.bd_drmwan.core.main.data.remote.source

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.data.remote.response.CreditResponse
import com.bd_drmwan.core.main.data.remote.response.GenresResponse
import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import com.bd_drmwan.core.main.services.MoviesService
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(
    private val service: MoviesService
) : BaseRemoteDataSource() {

    suspend fun getMovies(moviesType: MoviesType): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.getMovies(moviesType.path, apiKey, language, 1)
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

    suspend fun searchMovies(title: String): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.searchMovies(apiKey, title, language, 1)
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

    suspend fun getCreditMovie(movieId: Int): Flow<Resource<CreditResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.getCreditMovie(movieId, apiKey, language)
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

    suspend fun getGenreMovies(): Flow<Resource<GenresResponse>> {
        return flow {
            try {
                emit(Resource.Loading())
                val response = service.getGenreMovies(apiKey, language)
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