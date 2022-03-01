package com.bd_drmwan.core.main.data.remote.source

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.data.remote.response.MoviesResponse
import com.bd_drmwan.core.main.services.MoviesService
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MoviesRemoteDataSource(
    private val service: MoviesService
): BaseRemoteDataSource() {

    suspend fun getMovies(moviesType: MoviesType): Flow<Resource<MoviesResponse>> {
        return when(moviesType.name) {
            MoviesType.POPULAR.name -> getPopularMovies()
            MoviesType.TOP_RATED.name -> getPopularMovies()
            MoviesType.UPCOMING.name -> getPopularMovies()
            else -> getPopularMovies()
        }
    }

    private suspend fun getPopularMovies(): Flow<Resource<MoviesResponse>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = service.getPopularMovies(apiKey, language, 1)
                validateResponse(response,
                    onSuccess = {
                        emit(Resource.Success(it))
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
}