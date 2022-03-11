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
        return when (moviesType) {
            MoviesType.NOW_PLAYING -> getNowPlaying()
            MoviesType.TOP_RATED -> getTopRatedMovies()
            MoviesType.UPCOMING -> getUpComingMovies()
        }
    }

    suspend fun searchMovies(title: String): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading<MoviesResponse>())
            try {
                val response = service.searchMovies(apiKey, title, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<MoviesResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<MoviesResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<MoviesResponse>(errorMsg, errorType))
                }
            }
        }
    }

    private suspend fun getNowPlaying(): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading<MoviesResponse>())
            try {
                val response = service.getNowPlayingMovies(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<MoviesResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<MoviesResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<MoviesResponse>(errorMsg, errorType))
                }
            }
        }
    }

    private suspend fun getUpComingMovies(): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading<MoviesResponse>())
            try {
                val response = service.getUpComingMovies(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<MoviesResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<MoviesResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<MoviesResponse>(errorMsg, errorType))
                }
            }
        }
    }

    private suspend fun getTopRatedMovies(): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.getTopRatedMovies(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<MoviesResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<MoviesResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<MoviesResponse>(errorMsg, errorType))
                }
            }
        }
    }

    suspend fun getCreditMovie(movieId: Int): Flow<Resource<CreditResponse>> {
        return flow {
            emit(Resource.Loading<CreditResponse>())
            try {
                val response = service.getCreditMovie(movieId, apiKey, language)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<CreditResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<CreditResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<CreditResponse>(errorMsg, errorType))
                }
            }
        }
    }

    suspend fun getGenreMovies(): Flow<Resource<GenresResponse>> {
        return flow {
            try {
                emit(Resource.Loading<GenresResponse>())
                val response = service.getGenreMovies(apiKey, language)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success<GenresResponse>(it))
                    },
                    onError = { errorMsg ->
                        emit(Resource.Error<GenresResponse>(errorMsg))
                    }
                )
            } catch (e: Exception) {
                validateError(e) { errorMsg, errorType ->
                    emit(Resource.Error<GenresResponse>(errorMsg, errorType))
                }
            }
        }
    }
}