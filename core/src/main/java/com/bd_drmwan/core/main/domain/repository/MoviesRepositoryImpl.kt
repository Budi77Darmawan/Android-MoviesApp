package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.data.remote.source.MoviesRemoteDataSource
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import com.bd_drmwan.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource
): IMoviesRepository {
    override suspend fun getMovies(moviesType: MoviesType): Flow<Resource<List<MovieModel>>> {
        return flow {
            remoteDataSource.getMovies(moviesType).collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapMoviesResponseToMoviesModel(it.data)
                        emit(Resource.Success(data))
                    }
                }
            }
        }
    }

    override suspend fun searchMovies(title: String): Flow<Resource<List<MovieModel>>> {
        return flow {
            remoteDataSource.searchMovies(title).collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading())
                    is Resource.Error -> emit(Resource.Error(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapMoviesResponseToMoviesModel(it.data)
                        emit(Resource.Success(data))
                    }
                }
            }
        }
    }

}