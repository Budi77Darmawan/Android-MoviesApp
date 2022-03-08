package com.bd_drmwan.core.main.domain.repository

import com.bd_drmwan.core.enums.MoviesType
import com.bd_drmwan.core.main.domain.model.CastModel
import com.bd_drmwan.core.main.domain.model.MovieModel
import com.bd_drmwan.core.main.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IMoviesRepository {
    suspend fun getMovies(moviesType: MoviesType): Flow<Resource<List<MovieModel>>>
    suspend fun searchMovies(title: String): Flow<Resource<List<MovieModel>>>
    suspend fun getCastOnMovie(movieId: Int): Flow<Resource<List<CastModel>>>
    suspend fun getGenresMovie(): Flow<Resource<Any?>>
}