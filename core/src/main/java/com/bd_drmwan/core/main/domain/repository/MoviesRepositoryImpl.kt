package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.Genres
import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.data.remote.source.MoviesRemoteDataSource
import com.bd_drmwan.core.main.domain.model.CastModel
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
                    is Resource.Loading -> emit(Resource.Loading<List<MovieModel>>())
                    is Resource.Error -> emit(Resource.Error<List<MovieModel>>(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapMoviesResponseToMoviesModel(it.data)
                        emit(Resource.Success<List<MovieModel>>(data))
                    }
                }
            }
        }
    }

    override suspend fun searchMovies(title: String): Flow<Resource<List<MovieModel>>> {
        return flow {
            remoteDataSource.searchMovies(title).collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading<List<MovieModel>>())
                    is Resource.Error -> emit(Resource.Error<List<MovieModel>>(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapMoviesResponseToMoviesModel(it.data)
                        emit(Resource.Success<List<MovieModel>>(data))
                    }
                }
            }
        }
    }

    override suspend fun getCastOnMovie(movieId: Int): Flow<Resource<List<CastModel>>> {
        return flow {
            remoteDataSource.getCreditMovie(movieId).collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading<List<CastModel>>())
                    is Resource.Error -> emit(Resource.Error<List<CastModel>>(it.message, it.errorType))
                    is Resource.Success -> {
                        val data = DataMapper.mapCastResponseToCastModel(it.data?.cast)
                        emit(Resource.Success<List<CastModel>>(data))
                    }
                }
            }
        }
    }

    override suspend fun getGenresMovie(): Flow<Resource<Any?>> {
        return flow {
            remoteDataSource.getGenreMovies().collect {
                when (it) {
                    is Resource.Loading -> emit(Resource.Loading<Any?>())
                    is Resource.Error -> emit(Resource.Error<Any?>(it.message, it.errorType))
                    is Resource.Success -> {
                        Genres.setData(it.data?.genres)
                        emit(Resource.Success<Any?>(null))
                    }
                }
            }
        }
    }

}